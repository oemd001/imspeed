# Evaluating Cypress vs. Playwright for Migrating from TFWeb

## 1. Overview: TFWeb, Cypress, and Playwright

| Feature            | TFWeb (Current)          | Cypress                         | Playwright                      |
|--------------------|--------------------------|---------------------------------|---------------------------------|
| Developer Model    | Internal, proprietary JS API + wrappers
|                    | (synchronous style)      | Modern JS/TS, chainable syntax | Modern JS/TS, async/await style |
| Architecture       | Browser automation via custom drivers
|                    |                          | Runs in browser context         | Separate driver + browser context |
| Parallelization    | Limited, internal tooling| Supported via runners           | Built‑in test runner            |
| Platform Support   | Chrome (others unclear)  | Chrome, Firefox, Edge, Electron | Chromium, Firefox, WebKit       |
| Network Intercepts | Basic stubbing           | Advanced network stubbing/mock  | Advanced, includes WebSocket    |
| Test Isolation     | Custom sandboxing        | Automatic origin isolation      | Automatic, multiple contexts    |


### Key Differences: Playwright vs. Cypress

| Aspect                    | Cypress                                          | Playwright                                      |
|---------------------------|---------------------------------------------------|-------------------------------------------------|
| API Style                 | Chainable, synchronous-like                      | Async/await, promise-based                      |
| Browser Control           | Tests run inside browser, limited native APIs     | Out‑of‑process driver, more low‑level control    |
| Multi‑Browser Testing     | Chrome-family (Chromium, Electron), Firefox Beta  | Chromium, Firefox, WebKit (Safari)              |
| Parallelization & CI      | Needs separate runner setups; community packages  | Native parallelization and retries              |
| Network Mocking           | `cy.intercept()` for HTTP                        | `route()` and `network` for HTTP & WebSocket    |
| Mobile Emulation          | Limited                                           | Built-in device descriptors                     |
| Debugging                 | Time-travel snapshots, interactive GUI           | Video/screenshot captures, trace viewer         |
| Community & Ecosystem     | Mature, large community, rich plugins             | Rapidly growing, Microsoft-backed, flexible     |


## 2. Migrating Tests: When & Why?

- **Compatibility**: TFWeb tests use synchronous commands (`common.safeClick`, `wait(...).toBeVisible()`). Cypress’s chainable API is closer in style, easing migration.
- **Async Model**: Playwright’s async/await requires rewriting tests to `await page.click(...)` etc., but offers clearer flow control.
- **Features Needed**:
  - **Cross‑browser coverage**: Playwright preferred for WebKit/Safari support.
  - **Network stubbing**: Both support it, but Playwright’s support for WebSockets may be decisive.
  - **Parallel vs. CI integration**: Playwright has built‑in parallelism; Cypress requires runner setup.

**Recommendation**: If your priority is minimal refactoring and you primarily target Chromium‑based browsers, start with **Cypress**. If multi‑browser testing (including WebKit) or advanced scenarios (mobile emulation, WebSockets) are critical, invest in **Playwright**.


## 3. TFWeb vs. Cypress vs. Playwright: API & Workflow Similarities

| Operation                    | TFWeb                           | Cypress                                 | Playwright                                    |
|------------------------------|---------------------------------|-----------------------------------------|-----------------------------------------------|
| Navigating to URL            | `common.getCreateDealUrl(); open()` | `cy.visit(url)`                         | `await page.goto(url)`                        |
| Finding & Clicking Elements  | `common.safeClick(selector)`    | `cy.get(selector).click()`              | `await page.locator(selector).click()`        |
| Waiting for Visibility       | `wait(..., 1000).toBeVisible()` | `cy.get(selector).should('be.visible')` | `await page.locator(selector).waitFor({ state: 'visible' })` |
| Selecting from Dropdown      | `dropDown(...).setValue()`      | `cy.get(selector).select(value)`        | `await page.selectOption(selector, value)`    |
| Checking Checkboxes          | `checkBox(...).setValue(true)`  | `cy.get(selector).check()`              | `await page.locator(selector).check()`        |
| Saving or Submitting Forms   | `common.saveDeal()`             | `cy.get(saveBtn).click()`               | `await page.click(saveBtn)`                   |

**Similarity Analysis**:
- **TFWeb → Cypress**: Chainable commands and assertion style map directly. Minimal conceptual shift.
- **TFWeb → Playwright**: Tests become more explicit (async/await) and granular API calls, requiring modest restructuring.


## 4. Pros & Cons Summary

| Criteria                | Cypress                                                | Playwright                                            |
|-------------------------|--------------------------------------------------------|-------------------------------------------------------|
| **Ease of Migration**   | High – similar syntax, chainable API                   | Medium – async model differs but modern                  |
| **Browser Coverage**    | Chromium, Firefox Beta                                  | Chromium, Firefox, WebKit (Safari)                    |
| **Parallelization**     | Via plugins/runners; additional setup                  | Native parallel test execution                        |
| **Network Controls**    | HTTP stubbing, waiting                                 | HTTP & WebSocket stubbing, fine-grained intercept     |
| **Debugging & Reporting** | GUI dashboard, time-travel, snapshots                  | Trace viewer, video/screenshot playback                |
| **Ecosystem & Community** | Large plugin ecosystem, commercial dashboard (Cypress Cloud) | Growing ecosystem, Microsoft support, built-in features |
| **Mobile Testing**      | Limited emulation                                       | Full device descriptors & emulation                   |

---

**Next Steps**:
1. **Prototype Migration**:
   - Convert one TFWeb test to Cypress and one to Playwright.
   - Measure effort, CI integration, and reliability.
2. **Evaluate CI Flow**:
   - Configure parallel execution and reporting in your CI for both tools.
3. **Make Decision** based on:
   - Team familiarity.
   - Browser & feature requirements.
   - Maintenance overhead.

---

_This writeup aims to guide the team in selecting the right E2E testing framework as TFWeb reaches end of life. Feel free to iterate on the migration strategy based on your priorities._

