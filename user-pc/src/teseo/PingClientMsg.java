package teseo;

public class PingClientMsg extends net.tinyos.message.Message {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 6;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 83;

    /** Create a new PingClientMsg of size 6. */
    public PingClientMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new PingClientMsg of the given data_length. */
    public PingClientMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PingClientMsg with the given data_length
     * and base offset.
     */
    public PingClientMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PingClientMsg using the given byte array
     * as backing store.
     */
    public PingClientMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PingClientMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public PingClientMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PingClientMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public PingClientMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PingClientMsg embedded in the given message
     * at the given base offset.
     */
    public PingClientMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PingClientMsg embedded in the given message
     * at the given base offset and length.
     */
    public PingClientMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <PingClientMsg> \n";
      try {
        s += "  [pcktID=0x"+Long.toHexString(get_pcktID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [step=0x"+Long.toHexString(get_step())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [mobileNodeID=0x"+Long.toHexString(get_mobileNodeID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [power=0x"+Long.toHexString(get_power())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [channel=0x"+Long.toHexString(get_channel())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: pcktID
    //   Field type: short, unsigned
    //   Offset (bits): 0
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
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'pcktID'
     */
    public static int offsetBits_pcktID() {
        return 0;
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
    // Accessor methods for field: step
    //   Field type: int, unsigned
    //   Offset (bits): 8
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'step' is signed (false).
     */
    public static boolean isSigned_step() {
        return false;
    }

    /**
     * Return whether the field 'step' is an array (false).
     */
    public static boolean isArray_step() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'step'
     */
    public static int offset_step() {
        return (8 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'step'
     */
    public static int offsetBits_step() {
        return 8;
    }

    /**
     * Return the value (as a int) of the field 'step'
     */
    public int get_step() {
        return (int)getUIntBEElement(offsetBits_step(), 16);
    }

    /**
     * Set the value of the field 'step'
     */
    public void set_step(int value) {
        setUIntBEElement(offsetBits_step(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'step'
     */
    public static int size_step() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'step'
     */
    public static int sizeBits_step() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: mobileNodeID
    //   Field type: short, unsigned
    //   Offset (bits): 24
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'mobileNodeID' is signed (false).
     */
    public static boolean isSigned_mobileNodeID() {
        return false;
    }

    /**
     * Return whether the field 'mobileNodeID' is an array (false).
     */
    public static boolean isArray_mobileNodeID() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'mobileNodeID'
     */
    public static int offset_mobileNodeID() {
        return (24 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'mobileNodeID'
     */
    public static int offsetBits_mobileNodeID() {
        return 24;
    }

    /**
     * Return the value (as a short) of the field 'mobileNodeID'
     */
    public short get_mobileNodeID() {
        return (short)getUIntBEElement(offsetBits_mobileNodeID(), 8);
    }

    /**
     * Set the value of the field 'mobileNodeID'
     */
    public void set_mobileNodeID(short value) {
        setUIntBEElement(offsetBits_mobileNodeID(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'mobileNodeID'
     */
    public static int size_mobileNodeID() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'mobileNodeID'
     */
    public static int sizeBits_mobileNodeID() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: power
    //   Field type: short, unsigned
    //   Offset (bits): 32
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'power' is signed (false).
     */
    public static boolean isSigned_power() {
        return false;
    }

    /**
     * Return whether the field 'power' is an array (false).
     */
    public static boolean isArray_power() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'power'
     */
    public static int offset_power() {
        return (32 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'power'
     */
    public static int offsetBits_power() {
        return 32;
    }

    /**
     * Return the value (as a short) of the field 'power'
     */
    public short get_power() {
        return (short)getUIntBEElement(offsetBits_power(), 8);
    }

    /**
     * Set the value of the field 'power'
     */
    public void set_power(short value) {
        setUIntBEElement(offsetBits_power(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'power'
     */
    public static int size_power() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'power'
     */
    public static int sizeBits_power() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: channel
    //   Field type: short, unsigned
    //   Offset (bits): 40
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'channel' is signed (false).
     */
    public static boolean isSigned_channel() {
        return false;
    }

    /**
     * Return whether the field 'channel' is an array (false).
     */
    public static boolean isArray_channel() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'channel'
     */
    public static int offset_channel() {
        return (40 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'channel'
     */
    public static int offsetBits_channel() {
        return 40;
    }

    /**
     * Return the value (as a short) of the field 'channel'
     */
    public short get_channel() {
        return (short)getUIntBEElement(offsetBits_channel(), 8);
    }

    /**
     * Set the value of the field 'channel'
     */
    public void set_channel(short value) {
        setUIntBEElement(offsetBits_channel(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'channel'
     */
    public static int size_channel() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'channel'
     */
    public static int sizeBits_channel() {
        return 8;
    }

}
