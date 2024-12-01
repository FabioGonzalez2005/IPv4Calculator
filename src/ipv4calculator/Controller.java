package ipv4calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {
    private final Calculator view;

    public Controller(Calculator view) {
        this.view = view;
        this.view.setTitle("Calculadora IPv4");
        this.view.getBtnOK().addActionListener(this);
        this.view.getBtnClean().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnOK()) {
            try {
                String ip = view.getIpAddress().getText();
                String mask = view.getMask().getText();
                IPv4Address ipv4Address = new IPv4Address(ip, mask);

                String formato = "%-40s%-40s%n";

                String pantallaResultado = String.format("Address: " + formato, 
                        ipv4Address.getIPAddress(), ipv4Address.getBinaryAddress()) 
                        + String.format("Netmask: " + formato, 
                        ipv4Address.getDecimalMask(), ipv4Address.getBinaryMask()) 
                        + String.format("Net Address: " + formato, 
                        ipv4Address.getDecimalNetwork(), ipv4Address.getBinaryNetwork()) 
                        + String.format("HostMin: " + formato, 
                        ipv4Address.getDecimalFirstHost(), ipv4Address.getBinaryFirstHost()) 
                        + String.format("HostMax: " + formato, 
                        ipv4Address.getDecimalLastHost(), ipv4Address.getBinaryLastHost())
                        + String.format("Broadcast: " + formato, 
                        ipv4Address.getDecimalBroadcast(), ipv4Address.getBinaryBroadcast())
                        + String.format("Hosts/Net: %.0f%n", ipv4Address.getMaxHosts())
                        + String.format("Net Type: %s", ipv4Address.getType());

                view.getjTextArea1().setText(pantallaResultado);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al calcular:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == view.getBtnClean()) {
            view.getIpAddress().setText("");
            view.getMask().setText("");
        }
    }
}