import express from 'express';
import puppeteer from 'puppeteer';

const app = express();
const PORT = 5000;

app.get('/', (req, res) => {
    res.send('Express + TypeScript Server');
});

app.get('/crawl', async (req, res) => {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto('https://www.google.com');
    await page.screenshot({path: 'screenshot.png'});
    await browser.close();
})

app.listen(PORT, () => {
    console.log(`⚡️[server]: Server is running at localhost:${PORT}`);
});
