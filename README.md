# BankingSystemApplication

Project Description
This project is a minimalist, single-page web application designed to serve as the front-end interface for a digital wallet. It offers a simple, intuitive user experience that allows individuals to perform fundamental financial actions: checking their current balance and initiating transfers to other users. The application is built with a clean, responsive design, ensuring a seamless experience across various devices. Its core function is to act as a client, making asynchronous requests to a separate back-end service to perform all transactions and data management. This architecture demonstrates the principle of separation of concerns, where the front-end focuses exclusively on the user interface while a dedicated back-end handles the security and integrity of financial data.

Technologies Used
* HTML: The fundamental building block of the web page. It provides the structure for all the elements you see on the screen, such as headings, buttons, and forms.
* Tailwind CSS: A powerful utility-first CSS framework. Instead of writing custom CSS, Tailwind provides a set of pre-defined classes (e.g., flex, p-4, rounded-md) that are applied directly to the HTML. This approach enables rapid development of responsive and visually appealing designs.
* JavaScript: The core programming language of the web. It brings the application to life by handling user interactions, manipulating the HTML, and, most importantly, making the network requests to the back-end to manage financial data.

How to Run the App
 * To run this application, you only need to open the DigitalWalletApp.html file in a web browser.
   Due to its client-side nature, you can simply double-click the file or drag it into an open browser window.

Important Note: Functionality
Please be aware that this app will not be fully functional on its own. The JavaScript code inside the HTML file contains placeholder fetch requests that are trying to communicate with a hypothetical back-end server.
The core logic for sending money and updating balances resides on a separate back-end, which is not included in this file. As a result, the "Send" button will not work, and the balance will not update.

Next Steps
To make the application fully functional, you have a couple of options:

* Build a Back-End: Create a back-end service (for example, using Java) that exposes a REST API for the front-end to communicate with. You would then update the API_BASE_URL in the JavaScript to point to your new server.
* Create a Self-Contained App: We can create a new, self-contained version of this app where the back-end logic is simulated directly in JavaScript. This would allow the app to work without an external server.
