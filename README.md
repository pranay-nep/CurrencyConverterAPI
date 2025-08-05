# CurrencyConverterAPI

A Java-based Currency Converter application with a graphical user interface (GUI) built using Swing's JFrame.  
It fetches up-to-date exchange rates from [ExchangeRate-API](https://www.exchangerate-api.com) and allows conversion between over 60 currencies.

---

## Features

- Convert amounts between multiple world currencies  
- Real-time exchange rates fetched via ExchangeRate-API  
- User-friendly GUI with dropdown selections and input validation  
- Transaction history tracking  
- Swap currencies with a single click  

---

## Technologies Used

- Java (JDK 8 or higher)  
- Swing (JFrame) for GUI  
- [ExchangeRate-API](https://www.exchangerate-api.com) for exchange rates  
- JSON parsing via `org.json` library  

---

## Prerequisites

- Java Development Kit (JDK) 8 or higher installed  
- Internet connection (to fetch live exchange rates)  
- ExchangeRate-API API key (free tier available)  
- `org.json` JSON library (included or install separately)  

---

## Setup & Usage

1. **Clone the repository**

```bash
git clone https://github.com/pranay-nep/CurrencyConverterAPI.git
cd CurrencyConverterAPI
```

2. Add your API key

Create a file named config.properties in the root directory of the project include:

api_key=YOUR_EXCHANGE_RATE_API_KEY
base_currency=USD

    Replace YOUR_EXCHANGE_RATE_API_KEY with your actual API key from ExchangeRate-API.

    base_currency is optional; defaults to USD if not set.

3. Compile the project

If using the JSON library jar file (json-20231013.jar) included in the project, compile with:

    javac -cp json-20231013.jar CurrencyConverterOrganized.java
    
    
4. Run the application

    java -cp .:json-20231013.jar CurrencyConverterOrganized

*** Note: On Windows, replace : with ; in the classpath. ***
*** Note: If you are not using the .jar file included in the repo replace (json-20231013.jar) with the one you are using ***

How It Works

    Loads API key and base currency from config.properties.

    Fetches the latest exchange rates from the API.

    Populates currency dropdowns dynamically from fetched data.

    User inputs amount, selects currencies, and clicks to convert.

    Result is shown in a dialog box and transaction history is saved.

Configuration

    config.properties file controls your API key and base currency.

    The .gitignore file excludes config.properties to keep your API key private.

    To use a different base currency, just update base_currency in your config file.

Contribution

Contributions are welcome! Please follow these steps:

    Fork the repo

    Create your feature branch (git checkout -b feature/my-feature)

    Commit your changes (git commit -m 'Add some feature')

    Push to the branch (git push origin feature/my-feature)

    Open a pull request

License

This project is licensed under the MIT License.
Feel free to use, modify, and distribute with attribution.
Acknowledgments

    ExchangeRate-API for free currency exchange data

    org.json for JSON parsing

    Stack Overflow and Java documentation for coding guidance

    OpenAI’s ChatGPT for assistance with code and documentation

Contact

For questions or support, please open an issue or contact me at [pranaynep@gmail.com].

Thank you for checking out the Currency Converter project!
