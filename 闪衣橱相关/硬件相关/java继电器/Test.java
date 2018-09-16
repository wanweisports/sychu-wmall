import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.ip.IpParameters;

public class Test {
    public static void main(String[] args) throws Exception {
        ModbusFactory factory = new ModbusFactory();
        IpParameters params = new IpParameters();
        params.setHost("127.0.0.1");
        params.setPort(502);
        params.setEncapsulated(true);
        ModbusMaster master = factory.createTcpMaster(params, false);
        // master.setRetries(4);
        master.setTimeout(2000);
        master.setRetries(0);

        long start = System.currentTimeMillis();
        try {
            master.init();
            for (int i = 0; i < 100; i++) {
                System.out.println(master.getValue(127, RegisterRange.HOLDING_REGISTER, 1220,
                        DataType.TWO_BYTE_INT_UNSIGNED));
            }
        }
        finally {
            master.destroy();
        }

        System.out.println("Took: " + (System.currentTimeMillis() - start) + "ms");
    }

    // public static void main(String[] args) throws Exception {
    // ModbusFactory factory = new ModbusFactory();
    // IpParameters params = new IpParameters();
    // params.setHost("localhost");
    // params.setPort(12345);
    // ModbusMaster master = factory.createTcpMaster(params, true, false);
    // // master.setRetries(4);
    // master.setRetries(0);
    // try {
    // master.init();
    // master.getValue(1, RegisterRange.HOLDING_REGISTER, 0, DataType.TWO_BYTE_INT_UNSIGNED);
    // }
    // finally {
    // master.destroy();
    // }
    // }
}
