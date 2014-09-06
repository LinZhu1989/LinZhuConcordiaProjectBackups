package server;

/**
 * Interface definition: StationServerInterface.
 * 
 * @author OpenORB Compiler
 */
public interface StationServerInterface
{
    /**
     * Operation createCRecord
     */
    public OperationResult createCRecord(String badgeID, String firstName, String lastName, String description, char status);

    /**
     * Operation createMRecord
     */
    public OperationResult createMRecord(String badgeID, String firstName, String lastName, String address, String lastDate, String lastLocation, char status);

    /**
     * Operation getRecordCounts
     */
    public OperationResult getRecordCounts(String badgeID, char recordType);

    /**
     * Operation editCRecord
     */
    public OperationResult editCRecord(String badgeID, String lastName, String recordID, char newStatus);

    /**
     * Operation transferRecord
     */
    public OperationResult transferRecord(String badgeID, String recordID, String remoteStationServerName);

    /**
     * Operation transfer
     */
    public OperationResult transfer(String theRecord);
}
