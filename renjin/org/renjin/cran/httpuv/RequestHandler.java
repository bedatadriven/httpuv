package org.renjin.cran.httpuv;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.renjin.eval.Context;
import org.renjin.sexp.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class RequestHandler extends AbstractHandler {

  public static final Symbol SERVER_NAME = Symbol.get("SERVER_NAME");
  public static final Symbol SERVER_PORT = Symbol.get("SERVER_PORT");
  public static final Symbol REQUEST_METHOD = Symbol.get("REQUEST_METHOD");

  private final Context context;
  private final Function onHeadersSEXP;
  private final Function onBodyDataSEXP;
  private final Function onRequestSEXP;

  public RequestHandler(Context context, Function onHeadersSEXP, Function onBodyDataSEXP, Function onRequestSEXP) {
    this.context = context;
    this.onHeadersSEXP = onHeadersSEXP;
    this.onBodyDataSEXP = onBodyDataSEXP;
    this.onRequestSEXP = onRequestSEXP;
  }

  @Override
  public void handle(String s, Request request,
                     HttpServletRequest httpRequest,
                     HttpServletResponse httpResponse) throws IOException, ServletException {


    // Create an R environment to hold the request data
    HashFrame frame = new HashFrame();
    frame.setVariable(SERVER_NAME, StringVector.valueOf(httpRequest.getServerName()));
    frame.setVariable(REQUEST_METHOD, StringVector.valueOf(httpRequest.getMethod()));

    Environment req = Environment.createChildEnvironment(Environment.EMPTY, frame).build();

    // Pass the request object to the handler function
    // We need to ensure that we only execute one function call concurrently
    // in a single session.

    FunctionCall functionCall = FunctionCall.newCall(onRequestSEXP, req);
    ListVector response;
    synchronized (context.getSession()) {
      response = (ListVector) context.evaluate(functionCall);
    }

    // Convert the response from the function
    // and translate it back to the Jetty API

    httpResponse.setStatus(response.getElementAsInt("status"));

    SEXP headers = response.getElementAsSEXP("headers");
    if(headers instanceof StringVector) {
      StringVector headerVector = (StringVector) headers;
      for (int i = 0; i < headerVector.length(); i++) {
        String headerName = headerVector.getName(i);
        String headerValue = headerVector.getElementAsString(i);
        httpResponse.setHeader(headerName, headerValue);
      }
    }
    SEXP body = response.getElementAsSEXP("body");
    if(body instanceof RawVector) {
      ServletOutputStream outputStream = httpResponse.getOutputStream();
      RawVector bodyVector = (RawVector) body;
      outputStream.write(bodyVector.toByteArrayUnsafe());

    } else if(body instanceof StringVector) {
      PrintWriter writer = httpResponse.getWriter();
      StringVector bodyVector = (StringVector) body;
      for(String bodyElement : bodyVector) {
        writer.write(bodyElement);
      }
    }

    request.setHandled(true);
  }

  private Iterable<String> getHeaders(ListVector response) {
    SEXP headers = response.getElementAsSEXP("headers");
    if(headers instanceof StringVector) {
      return (StringVector) headers;
    } else {
      return Collections.emptySet();
    }
  }
}
