package rahul.raj.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManagerSingleton {
	private static ReportManagerSingleton reporterInstance;
    private final ExtentReports extent;

    private ReportManagerSingleton() {
        String path=System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("QKart- Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Rahul Raj");
    }

    public static ReportManagerSingleton getReporterInstance() {
        if(reporterInstance==null) {
        	reporterInstance=new ReportManagerSingleton();
        }
        return reporterInstance;
    }

    public ExtentReports getExtentReports() {
        return this.extent;
    }
}
