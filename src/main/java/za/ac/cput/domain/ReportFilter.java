package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class ReportFilter {
    @Id
    private Long filterId;
    private String filterName;
    private String filterValue;
    private String filterType;

    protected ReportFilter() {}

    private ReportFilter(Builder builder) {
        this.filterId = builder.filterId;
        this.filterName = builder.filterName;
        this.filterValue = builder.filterValue;
        this.filterType = builder.filterType;
    }

    // Getters
    public Long getFilterId() { return filterId; }
    public String getFilterName() { return filterName; }
    public String getFilterValue() { return filterValue; }
    public String getFilterType() { return filterType; }

    @Override
    public String toString() {
        return "ReportFilter{" +
                "filterId=" + filterId +
                ", filterName='" + filterName + '\'' +
                ", filterValue='" + filterValue + '\'' +
                '}';
    }

    public static class Builder {
        private Long filterId;
        private String filterName;
        private String filterValue;
        private String filterType;

        public Builder setFilterId(Long filterId) {
            this.filterId = filterId;
            return this;
        }

        public Builder setFilterName(String filterName) {
            this.filterName = filterName;
            return this;
        }

        public Builder setFilterValue(String filterValue) {
            this.filterValue = filterValue;
            return this;
        }

        public Builder setFilterType(String filterType) {
            this.filterType = filterType;
            return this;
        }

        public Builder copy(ReportFilter filter) {
            this.filterId = filter.filterId;
            this.filterName = filter.filterName;
            this.filterValue = filter.filterValue;
            this.filterType = filter.filterType;
            return this;
        }

        public ReportFilter build() {
            return new ReportFilter(this);
        }
    }
}