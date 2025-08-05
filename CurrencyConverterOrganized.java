package CurrencyConverterOrganized;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import org.json.JSONObject;


public class CurrencyConverterOrganized {
    private static Map<String, Double> fetchConversionRates() {
        
        Map<String, Double> conversionRates = new HashMap<>();
        try {
            /* 
            Note: This program retrieves the API key from an external config file (not included here for security reasons).
            To use this project, you will need to create your own `config.properties` file in the project directory.
            The file should contain: api_key=YOUR_API_KEY_HERE
            You can obtain a key by signing up at https://www.exchangerate-api.com
            Also, feel free to modify your copy of the source code to hardcode your own key if you prefer (Although it isn't recommended).
            More details about this step and other steps are included in the readme for this project
            */
            String apiKey = ConfigLoader.getApiKey();
            if (apiKey == null) {
                System.out.println("API key not found. Check your config file!");
                return null;
            }
            String baseCurrency = ConfigLoader.getBaseCurrency();
            if (baseCurrency == null) {
                baseCurrency = "USD"; 
                // in case you don't have a baseCurrency variable in your config file it will by default add USD to the end of the URL formed below
            }
            String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            in.close();
            conn.disconnect();

            JSONObject json = new JSONObject(content.toString());
            JSONObject rates = json.getJSONObject("conversion_rates");

            // Map API currency codes to human-readable names if you want
            Map<String, String> codeToName = new HashMap<>();
            codeToName.put("USD", "United States Dollar");
            codeToName.put("AED", "United Arab Emirates Dirham");
            codeToName.put("AFN", "Afghan Afghani");
            codeToName.put("ALL", "Albanian lek");
            codeToName.put("AMD", "Armenian Dram");
            codeToName.put("ANG", "Dutch Guilder");
            codeToName.put("AOA", "Angolan Kwanza");
            codeToName.put("ARS", "Argentine Peso");
            codeToName.put("AUD", "Australian Dollar");
            codeToName.put("AWG", "Aruban Florin");
            codeToName.put("AZN", "Azerbaijani Manat");
            codeToName.put("BAM", "Bosnia-Herzegovina Convertible Mark");
            codeToName.put("BBD", "Barbadian Dollar");
            codeToName.put("BDT", "Bangladeshi Taka");
            codeToName.put("BGN", "Bulgarian Lev");
            codeToName.put("BHD", "Bahraini Dinar");
            codeToName.put("BIF", "Burundian Franc");
            codeToName.put("BMD", "Bermudan Dollar");
            codeToName.put("BND", "Brunei Dollar");
            codeToName.put("BOB", "Bolivian Boliviano");
            codeToName.put("BRL", "Brazilian Real");
            codeToName.put("BSD", "Bahamian Dollar ");
            codeToName.put("BTN", "Bhutanese ngultrum");
            codeToName.put("BWP", "Botswana pula");
            codeToName.put("BYN", "Belarusian Ruble");
            codeToName.put("BZD", "Belize Dollar");
            codeToName.put("CAD", "Canadian Dollar");
            codeToName.put("CDF", "Congolese Franc");
            codeToName.put("CHF", "Swiss Franc");
            codeToName.put("CLP", "Chilean Peso");
            codeToName.put("CNY", "Chinese Yuan");
            codeToName.put("COP", "Colombian Peso");
            codeToName.put("CRC", "Costa Rican Colón");
            codeToName.put("CUP", "Cuban Peso");
            codeToName.put("CVE", "Cape Verdean Escudo");
            codeToName.put("CZK", "Czech Koruna");
            codeToName.put("DJF", "Djiboutian Franc");
            codeToName.put("DKK", "Danish Krone");
            codeToName.put("DOP", "Dominican Peso");
            codeToName.put("DZD", "Algerian Dinar");
            codeToName.put("EGP", "Egyptian Pound");
            codeToName.put("ERN", "Eritrean Nakfa");
            codeToName.put("ETB", "Ethiopian Birr");
            codeToName.put("EUR", "Euro");
            codeToName.put("FJD", "Fijian Dollar");
            codeToName.put("FKP", "Falkland Islands Pound");
            codeToName.put("FOK", "Faroese Króna");
            codeToName.put("GBP", "Pound sterling");
            codeToName.put("GEL", "Georgian Lari");
            codeToName.put("GGP", "Guernsey Pound");
            codeToName.put("GHS", "Ghanaian Cedi");
            codeToName.put("GIP", "Gibraltar pound");
            codeToName.put("GMD", "Gambian Dalasi");
            codeToName.put("GNF", "Guinean Franc");
            codeToName.put("GTQ", "Guatemalan Quetzal");
            codeToName.put("GYD", "Guyanese dollar");
            codeToName.put("HKD", "Hong Kong Dollar");
            codeToName.put("HNL", "Honduran Lempira");
            codeToName.put("HRK", "Croatian Kuna");
            codeToName.put("HTG", "Haitian Gourde");
            codeToName.put("HUF", "Hungarian Forint");
            codeToName.put("IDR", "Indonesian Rupiah");
            codeToName.put("ILS", "Israeli New Shekel");
            codeToName.put("IMP", "Manx pound");
            codeToName.put("IQD", "Iraqi Dinar");
            codeToName.put("IRR", "Iranian Rial");
            codeToName.put("ISK", "Icelandic Króna");
            codeToName.put("INR", "Indian Rupee");
            codeToName.put("JEP", "Jersey Pounds");
            codeToName.put("JMD", "Jamaican Dollar");
            codeToName.put("JOD", "Jordanian Dinar");
            codeToName.put("JPY", "Japanese Yen");
            codeToName.put("KES", "Kenyan Shilling");
            codeToName.put("KGS", "Kyrgystani Som");
            codeToName.put("KHR", "Cambodian riel");
            codeToName.put("KID", "Kiribati dollar");
            codeToName.put("KMF", "Comorian Franc");
            codeToName.put("KRW", "South Korean Won");
            codeToName.put("KWD", "Kuwaiti Dinar");
            codeToName.put("KYD", "Cayman Islands Dollar");
            codeToName.put("KZT", "Kazakhstani Tenge");
            codeToName.put("LAK", "Laotian Kip");
            codeToName.put("LBP", "Lebanese pound ");
            codeToName.put("LKR", "Sri Lankan Rupee");
            codeToName.put("LRD", "Liberian Dollar");
            codeToName.put("LSL", "Lesotho Loti ");
            codeToName.put("LYD", "Libyan Dinar");
            codeToName.put("MAD", "Moroccan Dirham");
            codeToName.put("MDL", "Moldovan Leu");
            codeToName.put("MGA", "Malagasy Ariary");
            codeToName.put("MKD", "Macedonian Denar");
            codeToName.put("MMK", "Myanmar Kyat");
            codeToName.put("MNT", "Mongolian Tugrik");
            codeToName.put("MOP", "Macanese Pataca");
            codeToName.put("MRU", "Mauritanian Ouguiya");
            codeToName.put("MUR", "Mauritian Rupee");
            codeToName.put("MVR", "Maldivian Rufiyaa");
            codeToName.put("MWK", "Malawian Kwacha");
            codeToName.put("MXN", "Mexican Peso");
            codeToName.put("MYR", "Malaysian Ringgit");
            codeToName.put("MZN", "Mozambican metical");
            codeToName.put("NAD", "Namibian Dollar");
            codeToName.put("NGN", "Nigerian Naira");
            codeToName.put("NIO", "Nicaraguan Córdoba");
            codeToName.put("NOK", "Norwegian Krone");
            codeToName.put("NPR", "Nepalese Rupee");
            codeToName.put("NZD", "New Zealand Dollar");
            codeToName.put("OMR", "Omani Rial");
            codeToName.put("PAB", "Panamanian Balboa");
            codeToName.put("PEN", "Peruvian Sol");
            codeToName.put("PGK", "Papua New Guinean Kina");
            codeToName.put("PHP", "Philippine peso");
            codeToName.put("PKR", "Pakistani Rupee");
            codeToName.put("PLN", "Polish złoty");
            codeToName.put("PYG", "Paraguayan Guarani");
            codeToName.put("QAR", "Qatari Riyal");
            codeToName.put("RON", "Romanian Leu");
            codeToName.put("RSD", "Serbian Dinar");
            codeToName.put("RUB", "Russian Ruble");
            codeToName.put("RWF", "Rwandan Franc");
            codeToName.put("SAR", "Saudi Riyal");
            codeToName.put("SBD", "Solomon Islands Dollar");
            codeToName.put("SCR", "Seychellois Rupee");
            codeToName.put("SDG", "Sudanese pound");
            codeToName.put("SEK", "Swedish Krona");
            codeToName.put("SGD", "Singapore Dollar");
            codeToName.put("SHP", "Saint Helena pound");
            codeToName.put("SLE", "Sierra Leonean Leone (SLE)");
            codeToName.put("SLL", "Sierra Leonean Leone (Old - SLL)");
            codeToName.put("SOS", "Somali Shilling");
            codeToName.put("SRD", "Surinamese Dollar");
            codeToName.put("SSP", "South Sudanese pound");
            codeToName.put("STN", "Sao Tomean Dobra");
            codeToName.put("SYP", "Syrian Pound");
            codeToName.put("SZL", "Swazi Lilangeni");
            codeToName.put("THB", "Thai Baht");
            codeToName.put("TJS", "Tajikistani Somoni");
            codeToName.put("TMT", "Turkmenistani Manat");
            codeToName.put("TND", "Tunisian Dinar");
            codeToName.put("TOP", "Tongan Paʻanga");
            codeToName.put("TRY", "Turkish lira");
            codeToName.put("TTD", "Trinidad & Tobago Dollar");
            codeToName.put("TVD", "Tuvaluan dollar");
            codeToName.put("TWD", "New Taiwan dollar");
            codeToName.put("TZS", "Tanzanian Shilling");
            codeToName.put("UAH", "Ukrainian hryvnia");
            codeToName.put("UGX", "Ugandan Shilling");
            codeToName.put("UYU", "Uruguayan Peso");
            codeToName.put("UZS", "Uzbekistani Som");
            codeToName.put("VES", "Venezuelan Bolívar");
            codeToName.put("VND", "Vietnamese dong");
            codeToName.put("VUV", "Vanuatu Vatu");
            codeToName.put("WST", "Samoan Tala");
            codeToName.put("XAF", "Central African CFA franc");
            codeToName.put("XCD", "East Caribbean Dollar");
            codeToName.put("XCG", "Caribbean guilder");
            codeToName.put("XDR", "Special Drawing Rights");
            codeToName.put("XOF", "West African CFA franc equals");
            codeToName.put("XPF", "CFP Franc");
            codeToName.put("YER", "Yemeni Rial");
            codeToName.put("ZAR", "South African Rand");
            codeToName.put("ZMW", "Zambian Kwacha");
            codeToName.put("ZWL", "Zimbabwean dollar");

            Iterator<String> keys = rates.keys();
            while (keys.hasNext()) {
                String code = keys.next();
                double rate = rates.getDouble(code);

                // Use mapped name if exists, else fallback to code
                String name = codeToName.getOrDefault(code, code);
                conversionRates.put(name, rate);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // If API fails, optionally fill conversionRates with some defaults or empty map
        }
        return conversionRates;
    }
    public static void main(String[] args) {

        

        Map<String, Double> conversionRates = fetchConversionRates();
        conversionRates.put("--Select Currency--", 0.00);

        // Creates a list of currency names, sorted alphabetically
        String[] currencies = conversionRates.keySet().toArray(new String[0]);
        Arrays.sort(currencies);

        // Creating the components for the GUI
        JComboBox<String> fromCurrency = new JComboBox<>(currencies); // Dropdown menu for "from" currency
        JComboBox<String> toCurrency = new JComboBox<>(currencies); // Dropdown menu for "to" currency
        JTextField amountField = new JTextField(); // Input field for the amount
        JButton swapButton = new JButton("Swap"); // Button to swap currencies
        JButton clearButton = new JButton("Clear "); // Button to clear selections
        JButton transactionHistory = new JButton("Transactions"); // Button to view transaction history
        DefaultListModel<String> historyModel = new DefaultListModel<>(); // List model for transaction history

        // Panel to hold components and set layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to vertical

        // ActionListener for Swap button
        swapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected currencies
                Object from = fromCurrency.getSelectedItem();
                Object to = toCurrency.getSelectedItem();
                
                // Swap the selected currencies
                fromCurrency.setSelectedItem(to);
                toCurrency.setSelectedItem(from);
            }
        });

        // ActionListener for Clear button
        clearButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                // Confirm with the user if they want to clear everything, double check
                int result = JOptionPane.showConfirmDialog(
                    null, 
                    "Are you sure you want to clear everything?", 
                    "Confirm Action", 
                    JOptionPane.YES_NO_OPTION
                );
                
                // Clear selections if the user confirms
                if (result == JOptionPane.YES_OPTION) {
                    fromCurrency.setSelectedItem("---Select Currency---");
                    toCurrency.setSelectedItem("---Select Currency---");
                }
            }
        });

        // ActionListener for Transaction History button
        transactionHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if there are any transactions to display
                if (historyModel.size() < 1) {
                    JOptionPane.showMessageDialog(null, "You have not completed any transactions yet.", "Transaction History", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Display transaction history
                    // Only if there is at least one
                    StringBuilder historyText = new StringBuilder();
                    for (int i = 0; i < historyModel.size(); i++) {
                        historyText.append(historyModel.getElementAt(i)).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, historyText.toString(), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Adding components to the main menu panel with Jlabels
        panel.add(new JLabel("Currency Converter"));
        panel.add(new JLabel("Convert from:"));
        panel.add(fromCurrency); // JComboBox for "from" currency
        panel.add(new JLabel("Convert to:"));
        panel.add(toCurrency); // JComboBox for "to" currency
        panel.add(new JLabel("Switch: "));
        panel.add(swapButton); // JButton for swapping currencies
        panel.add(new JLabel("Clear: "));
        panel.add(clearButton); // JButton for clearing selections
        panel.add(new JLabel("Enter amount:"));
        panel.add(amountField); // JTextField for entering amount
        panel.add(new JLabel("Transactions"));
        panel.add(transactionHistory); // JButton for viewing transaction history

        // Main loop for displaying the menu and handling user actions
        boolean stop = false;
        while (!stop) {
            // Display the main menu dialog box
            int result = JOptionPane.showConfirmDialog(null, panel, 
                    "Currency Converter Menu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Handle "Cancel" action (exit program)
            if (result == JOptionPane.CANCEL_OPTION) {
                int exit = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to leave the program?", "Currency Converter", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (exit == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null,"Thank you for using this program!");
                    break;
                }
            }
            
            // Handle "OK" action to perform conversion
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String from = (String) fromCurrency.getSelectedItem();
                    String to = (String) toCurrency.getSelectedItem();
                    double amount = Double.parseDouble(amountField.getText());

                    // Get conversion rates for selected currencies
                    double fromRate = conversionRates.get(from);
                    double toRate = conversionRates.get(to);

                    // If a currency are not selected, an error will occur
                    if (fromRate == 0 || toRate == 0) {
                        JOptionPane.showMessageDialog(
                                    null,
                                    "You need to select two currencies",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Convert the amount using the formula: amount * (toRate / fromRate)
                        double converted = amount * (toRate / fromRate);

                        // Display the conversion result
                        JOptionPane.showMessageDialog(null,
                            String.format("%.2f %s = %.2f %s", amount, from, converted, to));
                            
                        // Log the transaction in the history
                        String logEntry = String.format("%.2f %s = %.2f %s", amount, from, converted, to);
                        historyModel.addElement(logEntry);

                        // Ask if the user wants to perform another conversion
                        int stopChoice = JOptionPane.showConfirmDialog(null, 
                        "Would you like to run the program again?", "Currency Converter", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (stopChoice == JOptionPane.OK_OPTION) {
                            continue;
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Thank you for using this program!");
                            break;
                        } 
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number/currency.");
                }
            }
        }
    }
}

// used to load in my API Key
class ConfigLoader {
    public static String getApiKey() {
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("config.properties");
            prop.load(file);
            file.close();
            return prop.getProperty("api_key");
        } catch (IOException e) {
            // error if it cannot find a config file
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getBaseCurrency() {
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("config.properties");
            prop.load(file);
            file.close();
            return prop.getProperty("baseCurrency");
        } catch (IOException e) {
            // error if it cannot find a config file
            e.printStackTrace();
            return null;
        }
    }
}
