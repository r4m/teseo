package teseo;

public class DataMsg extends net.tinyos.message.Message {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 4;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 85;

    /** Create a new DataMsg of size 4. */
    public DataMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new DataMsg of the given data_length. */
    public DataMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DataMsg with the given data_length
     * and base offset.
     */
    public DataMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DataMsg using the given byte array
     * as backing store.
     */
    public DataMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DataMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public DataMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DataMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public DataMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DataMsg embedded in the given message
     * at the given base offset.
     */
    public DataMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DataMsg embedded in the given message
     * at the given base offset and length.
     */
    public DataMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <DataMsg> \n";
      try {
        s += "  [fixedNodeID=0x"+Long.toHexString(get_fixedNodeID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [pcktID=0x"+Long.toHexString(get_pcktID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [rss=0x"+Long.toHexString(get_rss())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [lqi=0x"+Long.toHexString(get_lqi())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: fixedNodeID
    //   Field type: short, unsigned
    //   Offset (bits): 0
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'fixedNodeID' is signed (false).
     */
    public static boolean isSigned_fixedNodeID() {
        return false;
    }

    /**
     * Return whether the field 'fixedNodeID' is an array (false).
     */
    public static boolean isArray_fixedNodeID() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'fixedNodeID'
     */
    public static int offset_fixedNodeID() {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'fixedNodeID'
     */
    public static int offsetBits_fixedNodeID() {
        return 0;
    }

    /**
     * Return the value (as a short) of the field 'fixedNodeID'
     */
    public short get_fixedNodeID() {
        return (short)getUIntBEElement(offsetBits_fixedNodeID(), 8);
    }

    /**
     * Set the value of the field 'fixedNodeID'
     */
    public void set_fixedNodeID(short value) {
        setUIntBEElement(offsetBits_fixedNodeID(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'fixedNodeID'
     */
    public static int size_fixedNodeID() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'fixedNodeID'
     */
    public static int sizeBits_fixedNodeID() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: pcktID
    //   Field type: short, unsigned
    //   Offset (bits): 8
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'pcktID' is signed (false).
     */
    public static boolean isSigned_pcktID() {
        return false;
    }

    /**
     * Return whether the field 'pcktID' is an array (false).
     */
    public static boolean isArray_pcktID() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'pcktID'
     */
    public static int offset_pcktID() {
        return (8 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'pcktID'
     */
    public static int offsetBits_pcktID() {
        return 8;
    }

    /**
     * Return the value (as a short) of the field 'pcktID'
     */
    public short get_pcktID() {
        return (short)getUIntBEElement(offsetBits_pcktID(), 8);
    }

    /**
     * Set the value of the field 'pcktID'
     */
    public void set_pcktID(short value) {
        setUIntBEElement(offsetBits_pcktID(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'pcktID'
     */
    public static int size_pcktID() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'pcktID'
     */
    public static int sizeBits_pcktID() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: rss
    //   Field type: byte, unsigned
    //   Offset (bits): 16
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'rss' is signed (false).
     */
    public static boolean isSigned_rss() {
        return false;
    }

    /**
     * Return whether the field 'rss' is an array (false).
     */
    public static boolean isArray_rss() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'rss'
     */
    public static int offset_rss() {
        return (16 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'rss'
     */
    public static int offsetBits_rss() {
        return 16;
    }

    /**
     * Return the value (as a byte) of the field 'rss'
     */
    public byte get_rss() {
        return (byte)getSIntBEElement(offsetBits_rss(), 8);
    }

    /**
     * Set the value of the field 'rss'
     */
    public void set_rss(byte value) {
        setSIntBEElement(offsetBits_rss(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'rss'
     */
    public static int size_rss() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'rss'
     */
    public static int sizeBits_rss() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: lqi
    //   Field type: short, unsigned
    //   Offset (bits): 24
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'lqi' is signed (false).
     */
    public static boolean isSigned_lqi() {
        return false;
    }

    /**
     * Return whether the field 'lqi' is an array (false).
     */
    public static boolean isArray_lqi() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'lqi'
     */
    public static int offset_lqi() {
        return (24 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'lqi'
     */
    public static int offsetBits_lqi() {
        return 24;
    }

    /**
     * Return the value (as a short) of the field 'lqi'
     */
    public short get_lqi() {
        return (short)getUIntBEElement(offsetBits_lqi(), 8);
    }

    /**
     * Set the value of the field 'lqi'
     */
    public void set_lqi(short value) {
        setUIntBEElement(offsetBits_lqi(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'lqi'
     */
    public static int size_lqi() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'lqi'
     */
    public static int sizeBits_lqi() {
        return 8;
    }

}
