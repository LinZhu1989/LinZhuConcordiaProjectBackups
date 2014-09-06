package FECORBAInterface;

/**
 * Interface definition: commonMethods.
 * 
 * @author OpenORB Compiler
 */
public interface commonMethodsOperations
{
    /**
     * Operation createCRecord
     */
    public String createCRecord(String firstName, String lastName, String description, String status, String badgeID);

    /**
     * Operation createMRecord
     */
    public String createMRecord(String firstName, String lastName, String address, String lastDate, String lastLocation, String status, String badgeID);

    /**
     * Operation getRecordCounts
     */
    public String getRecordCounts(String badgeID);

    /**
     * Operation editCRecord
     */
    public String editCRecord(String lastName, String recordID, String newStatus, String badgeID);

    /**
     * Operation transferRecord
     */
    public String transferRecord(String badgeID, String recordID, String remoteStationServerName);

    /**
     * Operation transfer
     */
    public boolean transfer(String record);

}
