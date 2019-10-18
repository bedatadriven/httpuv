package org.renjin.cran.httpuv;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.server.WebSocketUpgradeHandlerWrapper;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.renjin.eval.EvalException;
import org.renjin.primitives.Native;
import org.renjin.primitives.packaging.DllInfo;
import org.renjin.primitives.packaging.DllSymbol;
import org.renjin.sexp.ExternalPtr;
import org.renjin.sexp.Function;
import org.renjin.sexp.Null;
import org.renjin.sexp.SEXP;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.net.ServerSocket;

/**
 * Renjin drop-in replacement for httpuv's C/C++ code.
 */
public class httpuv {


  public static void R_init_httpuv(DllInfo dll) {
    dll.setUseDynamicSymbols(true);
  }

  public static SEXP httpuv_sendWSMessage(SEXP s1, SEXP s2, SEXP s3) {
    throw new UnsupportedOperationException("TODO");
  }


  public static SEXP httpuv_closeWS(SEXP s1, SEXP s2, SEXP s) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_makeTcpServer(
      SEXP hostSEXP,
      SEXP portSEXP,
      SEXP onHeadersSEXP,
      SEXP onBodyDataSEXP,
      SEXP onRequestSEXP,
      SEXP onWSOpenSEXP,
      SEXP onWSMessageSEXP,
      SEXP onWSCloseSEXP) throws Exception {

    String host = hostSEXP.asString();
    int port = portSEXP.asInt();

    Server server = new Server(port);
    server.setHandler(new RequestHandler(
        Native.currentContext(),
        (Function)onHeadersSEXP,
        (Function)onBodyDataSEXP,
        (Function)onRequestSEXP));

    server.setHandler(new WebSocketHandler() {
      @Override
      public void configure(WebSocketServletFactory factory) {
        throw new UnsupportedOperationException("TODO");
      }
    });

    server.start();
    server.join();

    return new ExternalPtr<>(server);
  }

  public static SEXP httpuv_makePipeServer(SEXP s1, SEXP s2, SEXP s3, SEXP s4, SEXP s5, SEXP s6, SEXP s7, SEXP s8) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_destroyServer(SEXP serverSexp) throws Exception {
    Server server = unpackServer(serverSexp);
    server.stop();

    return Null.INSTANCE;
  }

  public static SEXP httpuv_run(SEXP serverSexp) throws InterruptedException {
    Server server = unpackServer(serverSexp);
    server.join();

    return Null.INSTANCE;
  }


  public static SEXP httpuv_stopLoop() {
    throw new UnsupportedOperationException("TODO");
  }


  private static Server unpackServer(SEXP serverSexp) {
    if(!(serverSexp instanceof ExternalPtr)) {
      throw new EvalException("Expected an external pointer");
    }
    return (Server) ((ExternalPtr) serverSexp).getInstance();
  }

  public static SEXP httpuv_base64encode(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_daemonize(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_destroyDaemonizedServer(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_encodeURI(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_encodeURIComponent(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_decodeURI(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_decodeURIComponent(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_invokeCppCallback(SEXP s1, SEXP s2) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP httpuv_getRNGState() {
    throw new UnsupportedOperationException("TODO");
  }

}
