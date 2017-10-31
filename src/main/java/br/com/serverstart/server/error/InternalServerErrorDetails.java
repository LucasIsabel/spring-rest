package br.com.serverstart.server.error;

public class InternalServerErrorDetails extends ErrorDetails {

    private InternalServerErrorDetails() {}

    public static final class InternalServerErrorDetailsBuilder {
        private String title;
        private int status;
        private String details;
        private long timestemp;
        private String developerMessage;

        private InternalServerErrorDetailsBuilder() { }

        public static InternalServerErrorDetailsBuilder newBuilder() {
            return new InternalServerErrorDetailsBuilder();
        }

        public InternalServerErrorDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public InternalServerErrorDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public InternalServerErrorDetailsBuilder details(String details) {
            this.details = details;
            return this;
        }

        public InternalServerErrorDetailsBuilder timestemp(long timestemp) {
            this.timestemp = timestemp;
            return this;
        }

        public InternalServerErrorDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public InternalServerErrorDetails build() {
            InternalServerErrorDetails internalServerErrorDetails = new InternalServerErrorDetails();
            internalServerErrorDetails.setTimestemp(timestemp);
            internalServerErrorDetails.setDetails(details);
            internalServerErrorDetails.setStatus(status);
            internalServerErrorDetails.setTitle(title);
            internalServerErrorDetails.setDeveloperMessage(developerMessage);
            return internalServerErrorDetails;
        }
    }
}
