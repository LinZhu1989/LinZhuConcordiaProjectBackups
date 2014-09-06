package FECORBAInterface;

/**
 * Interface definition: commonMethods.
 * 
 * @author OpenORB Compiler
 */
public class _commonMethodsStub extends org.omg.CORBA.portable.ObjectImpl
        implements commonMethods
{
    static final String[] _ids_list =
    {
        "IDL:FECORBAInterface/commonMethods:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = FECORBAInterface.commonMethodsOperations.class;

    /**
     * Operation createCRecord
     */
    public String createCRecord(String firstName, String lastName, String description, String status, String badgeID)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("createCRecord",true);
                    _output.write_string(firstName);
                    _output.write_string(lastName);
                    _output.write_string(description);
                    _output.write_string(status);
                    _output.write_string(badgeID);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("createCRecord",_opsClass);
                if (_so == null)
                   continue;
                FECORBAInterface.commonMethodsOperations _self = (FECORBAInterface.commonMethodsOperations) _so.servant;
                try
                {
                    return _self.createCRecord( firstName,  lastName,  description,  status,  badgeID);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation createMRecord
     */
    public String createMRecord(String firstName, String lastName, String address, String lastDate, String lastLocation, String status, String badgeID)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("createMRecord",true);
                    _output.write_string(firstName);
                    _output.write_string(lastName);
                    _output.write_string(address);
                    _output.write_string(lastDate);
                    _output.write_string(lastLocation);
                    _output.write_string(status);
                    _output.write_string(badgeID);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("createMRecord",_opsClass);
                if (_so == null)
                   continue;
                FECORBAInterface.commonMethodsOperations _self = (FECORBAInterface.commonMethodsOperations) _so.servant;
                try
                {
                    return _self.createMRecord( firstName,  lastName,  address,  lastDate,  lastLocation,  status,  badgeID);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation getRecordCounts
     */
    public String getRecordCounts(String badgeID)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getRecordCounts",true);
                    _output.write_string(badgeID);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getRecordCounts",_opsClass);
                if (_so == null)
                   continue;
                FECORBAInterface.commonMethodsOperations _self = (FECORBAInterface.commonMethodsOperations) _so.servant;
                try
                {
                    return _self.getRecordCounts( badgeID);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation editCRecord
     */
    public String editCRecord(String lastName, String recordID, String newStatus, String badgeID)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("editCRecord",true);
                    _output.write_string(lastName);
                    _output.write_string(recordID);
                    _output.write_string(newStatus);
                    _output.write_string(badgeID);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("editCRecord",_opsClass);
                if (_so == null)
                   continue;
                FECORBAInterface.commonMethodsOperations _self = (FECORBAInterface.commonMethodsOperations) _so.servant;
                try
                {
                    return _self.editCRecord( lastName,  recordID,  newStatus,  badgeID);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation transferRecord
     */
    public String transferRecord(String badgeID, String recordID, String remoteStationServerName)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("transferRecord",true);
                    _output.write_string(badgeID);
                    _output.write_string(recordID);
                    _output.write_string(remoteStationServerName);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("transferRecord",_opsClass);
                if (_so == null)
                   continue;
                FECORBAInterface.commonMethodsOperations _self = (FECORBAInterface.commonMethodsOperations) _so.servant;
                try
                {
                    return _self.transferRecord( badgeID,  recordID,  remoteStationServerName);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation transfer
     */
    public boolean transfer(String record)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("transfer",true);
                    _output.write_string(record);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("transfer",_opsClass);
                if (_so == null)
                   continue;
                FECORBAInterface.commonMethodsOperations _self = (FECORBAInterface.commonMethodsOperations) _so.servant;
                try
                {
                    return _self.transfer( record);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
