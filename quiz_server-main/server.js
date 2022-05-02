

// Started from: ...

// server.js
const express = require('express');
// Define Express App
const app = express();
const PORT = process.env.PORT || 8080;
// Serve Static Assets
app.use(express.static('images'));
//app.use(express.static('questions'));
// Virtual Path Prefix '/static'
app.use('/static', express.static('images'))
//app.use('/static',  express.static('questions'));
app.use('/questions', (req, res) => {
    res.header("Content-Type",'application/json');
    res.sendFile("C:\\Users\\Sergiy's-PC\\Downloads\\quiz_server-main\\questions\\q_list1.json");
  });
app.get('/test', (req, res) => res.json({ "answer": 42 }));

app.listen(PORT, () => {
    console.log('Server connected at:', PORT);
});
