package org.renjin.cran.httpuv;

import org.renjin.sexp.SEXP;

/**
 * Renjin drop-in replacement for httpuv's C/C++ code.
 */
public class httpuv {

  public static SEXP _httpuv_sendWSMessage(SEXP s1, SEXP s2, SEXP s3) {
    throw new UnsupportedOperationException("TODO");
  }


  public static SEXP _httpuv_closeWS(SEXP s1, SEXP s2, SEXP s) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_makeTcpServer(SEXP s1, SEXP s2, SEXP s3, SEXP s4, SEXP s5, SEXP s6, SEXP s7, SEXP s8) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_makePipeServer(SEXP s1, SEXP s2, SEXP s3, SEXP s4, SEXP s5, SEXP s6, SEXP s7, SEXP s8) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_destroyServer(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_run(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }


  public static SEXP _httpuv_stopLoop() {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_base64encode(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_daemonize(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_destroyDaemonizedServer(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_encodeURI(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_encodeURIComponent(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_decodeURI(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_decodeURIComponent(SEXP s1) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_invokeCppCallback(SEXP s1, SEXP s2) {
    throw new UnsupportedOperationException("TODO");
  }

  public static SEXP _httpuv_getRNGState() {
    throw new UnsupportedOperationException("TODO");
  }

}
