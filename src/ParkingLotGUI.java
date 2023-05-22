import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class ParkingLotGUI extends JFrame {
    private PL parkingLot;
    private JTextArea parkedCarsTextArea;

    public ParkingLotGUI(int totalSpaces) {
        parkingLot = new PL(totalSpaces);

        setTitle("Parking Lot Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Apply Nimbus look and feel for a modern UI
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Handle the exception or fallback to default look and feel
        }

        JPanel controlPanel = new JPanel();
        JLabel licensePlateLabel = new JLabel("License Plate Number:");
        JTextField licensePlateField = new JTextField(10);
        JButton parkButton = new JButton("Park Car");
        JButton removeButton = new JButton("Remove Car");

        parkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String licensePlate = licensePlateField.getText();
                Car car = new Car(licensePlate, LocalDateTime.now());
                boolean parked = parkingLot.parkcar(car);
                if (parked) {
                    JOptionPane.showMessageDialog(ParkingLotGUI.this, "Car parked successfully.");
                    updateParkedCarsTextArea();
                } else {
                    JOptionPane.showMessageDialog(ParkingLotGUI.this, "No available parking spaces.");
                }
                licensePlateField.setText("");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String licensePlate = licensePlateField.getText();
                Car car = new Car(licensePlate, LocalDateTime.now());
                boolean removed = parkingLot.removeCar(car);
                if (removed) {
                    JOptionPane.showMessageDialog(ParkingLotGUI.this, "Car removed successfully.");
                    updateParkedCarsTextArea();
                } else {
                    JOptionPane.showMessageDialog(ParkingLotGUI.this, "Car not found in the parking lot.");
                }
                licensePlateField.setText("");
            }
        });

        controlPanel.add(licensePlateLabel);
        controlPanel.add(licensePlateField);
        controlPanel.add(parkButton);
        controlPanel.add(removeButton);

        parkedCarsTextArea = new JTextArea(10, 20);
        parkedCarsTextArea.setEditable(false);

        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(parkedCarsTextArea), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void updateParkedCarsTextArea() {
        List<Car> parkedCars = parkingLot.getParkedCar();
        StringBuilder sb = new StringBuilder();
        for (Car car : parkedCars) {
            sb.append("Registration Number: ").append(car.getRegNo()).append("\n");
            sb.append("Timestamp: ").append(car.getFormattedTimestamp()).append("\n\n");
        }
        parkedCarsTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ParkingLotGUI parkingLotGUI = new ParkingLotGUI(10);
                parkingLotGUI.setVisible(true);
            }
        });
    }
}
