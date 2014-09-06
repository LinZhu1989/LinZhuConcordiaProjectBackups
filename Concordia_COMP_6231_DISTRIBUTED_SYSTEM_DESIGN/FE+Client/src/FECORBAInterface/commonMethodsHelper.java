package FECORBAInterface;

/** 
 * Helper class for : commonMethods
 *  
 * @author OpenORB Compiler
 */ 
public class commonMethodsHelper
{
    /**
     * Insert commonMethods into an any
     * @param a an any
     * @param t commonMethods value
     */
    public static void insert(org.omg.CORBA.Any a, FECORBAInterface.commonMethods t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract commonMethods from an any
     *
     * @param a an any
     * @return the extracted commonMethods value
     */
    public static FECORBAInterface.commonMethods extract( org.omg.CORBA.Any a )
    {
        if ( !a.type().equivalent( type() ) )
        {
            throw new org.omg.CORBA.MARSHAL();
        }
        try
        {
            return FECORBAInterface.commonMethodsHelper.narrow( a.extract_Object() );
        }
        catch ( final org.omg.CORBA.BAD_PARAM e )
        {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the commonMethods TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc( id(), "commonMethods" );
        }
        return _tc;
    }

    /**
     * Return the commonMethods IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:FECORBAInterface/commonMethods:1.0";

    /**
     * Read commonMethods from a marshalled stream
     * @param istream the input stream
     * @return the readed commonMethods value
     */
    public static FECORBAInterface.commonMethods read(org.omg.CORBA.portable.InputStream istream)
    {
        return(FECORBAInterface.commonMethods)istream.read_Object(FECORBAInterface._commonMethodsStub.class);
    }

    /**
     * Write commonMethods into a marshalled stream
     * @param ostream the output stream
     * @param value commonMethods value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, FECORBAInterface.commonMethods value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to commonMethods
     * @param obj the CORBA Object
     * @return commonMethods Object
     */
    public static commonMethods narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof commonMethods)
            return (commonMethods)obj;

        if (obj._is_a(id()))
        {
            _commonMethodsStub stub = new _commonMethodsStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to commonMethods
     * @param obj the CORBA Object
     * @return commonMethods Object
     */
    public static commonMethods unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof commonMethods)
            return (commonMethods)obj;

        _commonMethodsStub stub = new _commonMethodsStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
