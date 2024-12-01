package ipv4calculator;

public class IPv4Address {
    // Atributos.
    private String IPAddress;
    private String mask;
    
    private String binaryAddress;
    
    private int intMask;
    private String binaryMask;
    private String decimalMask;
    private String CIDRMask;
    
    private String binaryNetwork;
    private String decimalNetwork;

    private String binaryBroadcast;
    private String decimalBroadcast;

    private String binaryFirstHost;
    private String decimalFirstHost;

    private String binaryLastHost;
    private String decimalLastHost;

    private double maxHosts;

    private String type;
    
    // Constructor.
    public IPv4Address(String IPAddress, String mask) {
        this.IPAddress = IPAddress;
        this.mask = mask;
        
        this.binaryAddress = IPAddressToBinary();
        
        this.intMask = Integer.valueOf(mask);
        this.binaryMask = "1".repeat(intMask) + "0".repeat(32 - intMask);
        this.decimalMask = binaryToDecimal(binaryMask);
        this.CIDRMask = "/" + intMask;
        
        this.binaryNetwork = binaryAddress.substring(0, intMask) + "0".repeat(32 - intMask);
        this.decimalNetwork = binaryToDecimal(binaryNetwork);

        this.binaryBroadcast = binaryAddress.substring(0, intMask) + "1".repeat(32 - intMask);
        this.decimalBroadcast = binaryToDecimal(binaryBroadcast);

        this.binaryFirstHost = binaryNetwork.substring(0, 31) + "1";
        this.decimalFirstHost = binaryToDecimal(binaryFirstHost);

        this.binaryLastHost = binaryBroadcast.substring(0, 31) + "0";
        this.decimalLastHost = binaryToDecimal(binaryLastHost);
        
        this.maxHosts = Math.pow(2, 32 - intMask) - 2;
        
        this.type = addressType();

    } 
    // Getters.
    public String getIPAddress() {
        return IPAddress;
    }
    public String getBinaryAddress() {
        return binaryNotation(binaryAddress);
    }
    public String getBinaryMask() {
        return binaryNotation(binaryMask);
    }
    public String getDecimalMask() {
        return decimalMask;
    }
    public String getCIDRMask() {
        return CIDRMask;
    }
    public String getBinaryNetwork() {
        return binaryNotation(binaryNetwork);
    }
    public String getDecimalNetwork() {
        return decimalNetwork;
    }
    public String getBinaryBroadcast() {
        return binaryNotation(binaryBroadcast);
    }
    public String getDecimalBroadcast() {
        return decimalBroadcast;
    }
    public String getBinaryFirstHost() {
        return binaryNotation(binaryFirstHost);
    }
    public String getDecimalFirstHost() {
        return decimalFirstHost;
    }
    public String getBinaryLastHost() {
        return binaryNotation(binaryLastHost);
    }
    public String getDecimalLastHost() {
        return decimalLastHost;
    }
    public double getMaxHosts() {
        return maxHosts;
    }
    public String getType() {
        return type;
    }
    // Pasar una dirección a binario.
    private String IPAddressToBinary() {
        String result = "";
        String[] octets = IPAddress.split("\\.");
        for (String octet : octets) {
            result += String.format("%8s", Integer.toBinaryString(Integer.valueOf(octet))).replace(" ", "0");
        }
        return result;
    }
    // Mostrar una direccion de 32 bits en grupos de 8.
    private String binaryNotation(String binary) {
        return binary.substring(0, 8) + "." +
                binary.substring(8, 16) + "." +
                binary.substring(16, 24) + "." +
                binary.substring(24, 32);
    }
    // Pasar una dirección de binario a decimal.
    private String binaryToDecimal(String binary) {
        return Integer.parseInt(binary.substring(0, 8), 2) + "." +
        Integer.parseInt(binary.substring(8, 16), 2) + "." +
        Integer.parseInt(binary.substring(16, 24), 2) + "." +
        Integer.parseInt(binary.substring(24, 32), 2);
    }
    // Averiguar el tipo de IP: pública o privada.
    private String addressType() {
        String formatted = "";
        String[] octets = IPAddress.split("\\.");
        for (String octet : octets) {
            formatted += String.format("%3s", octet).replace(" ", "0") + ".";
        }
        formatted = formatted.substring(0, 15);
        // 192.168.000.001
        if ( ( (formatted.compareTo("010.000.000.000") >= 0) && (formatted.compareTo("010.255.255.255") <= 0) ) ||
             ( (formatted.compareTo("172.016.000.000") >= 0) && (formatted.compareTo("172.031.255.255") <= 0) ) ||
             ( (formatted.compareTo("192.168.000.000") >= 0) && (formatted.compareTo("192.168.255.255") <= 0) ) ) {
            return "Private";
        } else {
            return "Public";
        }
    }
}