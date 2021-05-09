"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const tslib_1 = require("tslib");
const express_1 = tslib_1.__importDefault(require("express"));
const puppeteer_1 = tslib_1.__importDefault(require("puppeteer"));
const app = express_1.default();
const PORT = 5000;
app.get("/", (req, res) => {
    res.send("Express + TypeScript Server");
});
app.get("/crawl", (req, res) => tslib_1.__awaiter(void 0, void 0, void 0, function* () {
    const browser = yield puppeteer_1.default.launch();
    const page = yield browser.newPage();
    yield page.goto('https://www.google.com');
    yield page.screenshot({ path: 'screenshot.png' });
    yield browser.close();
}));
app.listen(PORT, () => {
    console.log(`⚡️[server]: Server is running at https://localhost:${PORT}`);
});
//# sourceMappingURL=index.js.map