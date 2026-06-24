package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Report {
    @Id
    private Long reportId;
    private String reportName;
    private String reportType;
    private LocalDateTime generatedDate;
    private String format;
    private String description;

    @ManyToOne
    @JoinColumn(name = "generated_by")
    private Admin generatedBy;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private List<ReportFilter> filters;

    protected Report() {}

    private Report(Builder builder) {
        this.reportId = builder.reportId;
        this.reportName = builder.reportName;
        this.reportType = builder.reportType;
        this.generatedDate = builder.generatedDate;
        this.format = builder.format;
        this.description = builder.description;
        this.generatedBy = builder.generatedBy;
        this.filters = builder.filters;
    }

    // Getters
    public Long getReportId() { return reportId; }
    public String getReportName() { return reportName; }
    public String getReportType() { return reportType; }
    public LocalDateTime getGeneratedDate() { return generatedDate; }
    public String getFormat() { return format; }
    public String getDescription() { return description; }
    public Admin getGeneratedBy() { return generatedBy; }
    public List<ReportFilter> getFilters() { return filters; }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportName='" + reportName + '\'' +
                ", reportType='" + reportType + '\'' +
                ", generatedDate=" + generatedDate +
                '}';
    }

    public static class Builder {
        private Long reportId;
        private String reportName;
        private String reportType;
        private LocalDateTime generatedDate = LocalDateTime.now();
        private String format;
        private String description;
        private Admin generatedBy;
        private List<ReportFilter> filters;

        public Builder setReportId(Long reportId) {
            this.reportId = reportId;
            return this;
        }

        public Builder setReportName(String reportName) {
            this.reportName = reportName;
            return this;
        }

        public Builder setReportType(String reportType) {
            this.reportType = reportType;
            return this;
        }

        public Builder setGeneratedDate(LocalDateTime generatedDate) {
            this.generatedDate = generatedDate;
            return this;
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setGeneratedBy(Admin generatedBy) {
            this.generatedBy = generatedBy;
            return this;
        }

        public Builder setFilters(List<ReportFilter> filters) {
            this.filters = filters;
            return this;
        }

        public Builder copy(Report report) {
            this.reportId = report.reportId;
            this.reportName = report.reportName;
            this.reportType = report.reportType;
            this.generatedDate = report.generatedDate;
            this.format = report.format;
            this.description = report.description;
            this.generatedBy = report.generatedBy;
            this.filters = report.filters;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }
}