package com.hannuus.gamble.utils;

import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StdOutErrRedirect {
	private final static Log logger = LogFactory
			.getLog(StdOutErrRedirect.class);

	public static void redirectSystemOutAndErrToLog() {
		PrintStream printStreamForOut = createLoggingWrapper(System.out, false);
		PrintStream printStreamForErr = createLoggingWrapper(System.out, true);
		System.setOut(printStreamForOut);
		System.setErr(printStreamForErr);
	}

	public static PrintStream createLoggingWrapper(final PrintStream printStream, final boolean isErr) {
		return new PrintStream(printStream) {
			@Override
			public void print(final String string) {
				if (!isErr) {
					logger.debug(string);
				} else {
					logger.error(string);
				}
			}

			@Override
			public void print(boolean b) {
				if (!isErr) {
					logger.debug(Boolean.valueOf(b));
				} else {
					logger.error(Boolean.valueOf(b));
				}
			}

			@Override
			public void print(char c) {
				if (!isErr) {
					logger.debug(Character.valueOf(c));
				} else {
					logger.error(Character.valueOf(c));
				}
			}

			@Override
			public void print(int i) {
				if (!isErr) {
					logger.debug(String.valueOf(i));
				} else {
					logger.error(String.valueOf(i));
				}
			}

			@Override
			public void print(long l) {
				if (!isErr) {
					logger.debug(String.valueOf(l));
				} else {
					logger.error(String.valueOf(l));
				}
			}

			@Override
			public void print(float f) {
				if (!isErr) {
					logger.debug(String.valueOf(f));
				} else {
					logger.error(String.valueOf(f));
				}
			}

			@Override
			public void print(double d) {
				if (!isErr) {
					logger.debug(String.valueOf(d));
				} else {
					logger.error(String.valueOf(d));
				}
			}

			@Override
			public void print(char[] x) {
				if (!isErr) {
					logger.debug(x == null ? null : new String(x));
				} else {
					logger.error(x == null ? null : new String(x));
				}
			}

			@Override
			public void print(Object obj) {
				if (!isErr) {
					logger.debug(obj);
				} else {
					logger.error(obj);
				}
			}
		};
	}
}