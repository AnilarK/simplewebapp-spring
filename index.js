const express = require('express');
const path = require('path');
const app = express();
const port = 8080;

app.get('/', (req,res,next) => {
    res.send('Samosa kha lo');
})

app.get('/about', (req,res,next) => {
    res.send('Samosa kha lo with chutney');
})

app.get('/file', (req,res,next) => {
    res.sendFile(path.join(__dirname, 'index.html'));
})

app.listen(port,()=>{
    console.log(`Server is running on port ${port}`);
})