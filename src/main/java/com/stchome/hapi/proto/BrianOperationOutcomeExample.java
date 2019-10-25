/*
 * 
 */
package com.stchome.hapi.proto;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hl7.fhir.r5.model.Narrative;
import org.hl7.fhir.r5.model.OperationOutcome;

/**
 *
 * @author blee
 */
public class BrianOperationOutcomeExample {

    /**
     * HTTP 404 (Not Found)
     * <p>
     * Requested resource doesn't exist
     */
    public static String buildClientFault404() throws JsonProcessingException {
        OperationOutcome operationOutcome = new OperationOutcome();
        operationOutcome.setId("clientFaultNotFound");
        operationOutcome.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
        operationOutcome.getText().setDivAsString("Resource Not Found. HTTP 404 Not Found");
        OperationOutcome.OperationOutcomeIssueComponent issue = operationOutcome.addIssue();
        issue.setSeverity(OperationOutcome.IssueSeverity.FATAL);
        issue.setCode(OperationOutcome.IssueType.NOTFOUND);
        issue.getDetails().setText("Resource Not Found. HTTP 404 Not Found");

        FhirContext ctx = FhirContext.forR5();
        IParser jsonParser = ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        String encoded = jsonParser.encodeResourceToString(operationOutcome);
        return encoded;
    }

    /**
     * HTTP 405 (Method Not Allowed)
     * <p>
     * HTTP operation not supported
     */
    public static String buildClientFault405() throws JsonProcessingException {
        OperationOutcome operationOutcome = new OperationOutcome();
        operationOutcome.setId("clientFaultNotAllowed");
        operationOutcome.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
        operationOutcome.getText().setDivAsString("Method Not Allowed. HTTP 405 Method Not Allowed");
        OperationOutcome.OperationOutcomeIssueComponent issue = operationOutcome.addIssue();
        issue.setSeverity(OperationOutcome.IssueSeverity.FATAL);
        issue.setCode(OperationOutcome.IssueType.NOTSUPPORTED);
        issue.getDetails().setText("Method Not Allowed. HTTP 405 Method Not Allowed");
        FhirContext ctx = FhirContext.forR5();
        IParser jsonParser = ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        String encoded = jsonParser.encodeResourceToString(operationOutcome);
        return encoded;
    }

    /**
     * HTTP 422 (Unprocessable Entity)
     * <p>
     * Invalid data or condition from client
     */
    public static String buildClientFault422() throws JsonProcessingException {
        OperationOutcome operationOutcome = new OperationOutcome();
        operationOutcome.setId("clientFaultValidation");
        operationOutcome.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
        operationOutcome.getText().setDivAsString("Illogical Date error: Assessment date must be after date of birth");
        OperationOutcome.OperationOutcomeIssueComponent issue = operationOutcome.addIssue();
        issue.setSeverity(OperationOutcome.IssueSeverity.ERROR);
        issue.setCode(OperationOutcome.IssueType.STRUCTURE);
        issue.getDetails().setText("Illogical Date error: Assessment date must be after date of birth");

        FhirContext ctx = FhirContext.forR5();
        IParser jsonParser = ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        String encoded = jsonParser.encodeResourceToString(operationOutcome);
        return encoded;
    }

    /**
     * HTTP 503
     * <p>
     * Transient Exception - try again, it might get better
     */
    public static String buildTransientFault503() throws JsonProcessingException {
        OperationOutcome operationOutcome = new OperationOutcome();
        operationOutcome.setId("serverFaultTransient");
        operationOutcome.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
        operationOutcome.getText().setDivAsString("No connections available in pool");
        OperationOutcome.OperationOutcomeIssueComponent issue = operationOutcome.addIssue();
        issue.setSeverity(OperationOutcome.IssueSeverity.ERROR);
        issue.setCode(OperationOutcome.IssueType.TRANSIENT);
        issue.getDetails().setText("No connections available in pool");
        FhirContext ctx = FhirContext.forR5();
        IParser jsonParser = ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        String encoded = jsonParser.encodeResourceToString(operationOutcome);
        return encoded;
    }

    /**
     * HTTP 500
     * <p>
     * Non Transient Exception - not getting any better
     */
    public static String buildNonTransientFault500() throws JsonProcessingException {
        OperationOutcome operationOutcome = new OperationOutcome();
        operationOutcome.setId("serverFaultNonTransient");
        operationOutcome.getText().setStatus(Narrative.NarrativeStatus.GENERATED);
        operationOutcome.getText().setDivAsString("XML schedule file not found");
        OperationOutcome.OperationOutcomeIssueComponent issue = operationOutcome.addIssue();
        issue.setSeverity(OperationOutcome.IssueSeverity.FATAL);
        issue.setCode(OperationOutcome.IssueType.EXCEPTION);
        issue.getDetails().setText("XML schedule file not found");
        FhirContext ctx = FhirContext.forR5();
        IParser jsonParser = ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        String encoded = jsonParser.encodeResourceToString(operationOutcome);
        return encoded;
    }

}
