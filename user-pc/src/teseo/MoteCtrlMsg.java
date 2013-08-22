package teseo;

public class MoteCtrlMsg extends net.tinyos.message.Message {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 1;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 86;

    /** Create a new MoteCtrlMsg of size 1. */
    public MoteCtrlMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new MoteCtrlMsg of the given data_length. */
    public MoteCtrlMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new MoteCtrlMsg with the given data_length
     * and base offset.
     */
    public MoteCtrlMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new MoteCtrlMsg using the given byte array
     * as backing store.
     */
    public MoteCtrlMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new MoteCtrlMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public MoteCtrlMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new MoteCtrlMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public MoteCtrlMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new MoteCtrlMsg embedded in the given message
     * at the given base offset.
     */
    public MoteCtrlMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new MoteCtrlMsg embedded in the given message
     * at the given base offset and length.
     */
    public MoteCtrlMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <MoteCtrlMsg> \n";
      try {
        s += "  [work=0x"+Long.toHexString(get_work())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: work
    //   Field type: short, unsigned
    //   Offset (bits): 0
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'work' is signed (false).
     */
    public static boolean isSigned_work() {
        return false;
    }

    /**
     * Return whether the field 'work' is an array (false).
     */
    public static boolean isArray_work() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'work'
     */
    public static int offset_work() {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'work'
     */
    public static int offsetBits_work() {
        return 0;
    }

    /**
     * Return the value (as a short) of the field 'work'
     */
    public short get_work() {
        return (short)getUIntBEElement(offsetBits_work(), 8);
    }

    /**
     * Set the value of the field 'work'
     */
    public void set_work(short value) {
        setUIntBEElement(offsetBits_work(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'work'
     */
    public static int size_work() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'work'
     */
    public static int sizeBits_work() {
        return 8;
    }

}
