package net.sociuris.minelw.crash;

public class ReportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final CrashReport crashReport;

	public ReportedException(CrashReport report) {
		this.crashReport = report;
	}

	public CrashReport getCrashReport() {
		return crashReport;
	}

	@Override
	public Throwable getCause() {
		return crashReport.getThrowable();
	}

	@Override
	public String getMessage() {
		return crashReport.getDescription();
	}

}