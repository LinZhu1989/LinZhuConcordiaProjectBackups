package FECORBAInterface;

/**
 * Holder class for : commonMethods
 * 
 * @author OpenORB Compiler
 */
final public class commonMethodsHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal commonMethods value
     */
    public FECORBAInterface.commonMethods value;

    /**
     * Default constructor
     */
    public commonMethodsHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public commonMethodsHolder(FECORBAInterface.commonMethods initial)
    {
        value = initial;
    }

    /**
     * Read commonMethods from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = commonMethodsHelper.read(istream);
    }

    /**
     * Write commonMethods into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        commonMethodsHelper.write(ostream,value);
    }

    /**
     * Return the commonMethods TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return commonMethodsHelper.type();
    }

}
