package br.com.serverstart.server.error;

public class ResourceNotFoundDetails extends ErrorDetails {

    private ResourceNotFoundDetails() { }

    public static final class Builder {
        private String title;
        private int status;
        private String details;
        private long timestemp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder timestemp(long timestemp) {
            this.timestemp = timestemp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setDetails(details);
            resourceNotFoundDetails.setTimestemp(timestemp);
            resourceNotFoundDetails.setStatus(status);
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            return resourceNotFoundDetails;
        }
    }
}
