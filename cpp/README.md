```
// tests/mapping-link.spec.ts
import { test, expect, Page } from '@playwright/test';

test('click UUID link in Mapping tab', async ({ page, context }) => {
  // 1️⃣ Direct‑load the Mapping tab
  await page.goto(
    'https://qa.ibdweb.site.gs.com/ecm-syndicate-link/#/deal/dealSetup/190423/mappings'
  );

  // 2️⃣ Locate the published UUID link (anchored text that matches a v4‑style pattern)
  const uuidLink = page.getByRole('link', {
    name: /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i
  });

  await expect(uuidLink).toBeVisible();

  // 3️⃣ Clicking may open either the same tab or a popup; handle both
  const [maybePopup] = await Promise.all([
    context.waitForEvent('page').catch(() => null), // resolves if popup appears
    uuidLink.click()
  ]);

  // 4️⃣ Verify navigation succeeded
  if (maybePopup) {
    // Link opened a new tab
    await maybePopup.waitForLoadState();
    await expect(maybePopup).toHaveURL(/https?:\/\//); // tighten regex if you know the domain
  } else {
    // Navigation stayed in the same tab
    await expect(page).toHaveURL(/https?:\/\//); // tighten as needed
  }
});
```
