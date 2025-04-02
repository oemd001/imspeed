## Pre-Requisites

### 1. CMG Indications Missing

| Requirement                     | Description                                                                                                  |
|--------------------------------|--------------------------------------------------------------------------------------------------------------|
| Brio Indications Exist          | The indication(s) must already be present in **Brio** and tied to a valid `BrioId`.                         |
| Valid Deal ID                  | Ensure a valid `dealId` is available and corresponds to the Brio record.                                     |
| Swagger Access                 | Confirm access to [ReflowBrioIndicationsToCmg](https://cmgint.brio.fg.url.gs.com/swagger-ui/index.html?#/indication-admin-facade/reflowBrioIndicationsToCmg). |

---

### 2. Brio Indications Missing / Mismatched

| Requirement                     | Description                                                                                                                                       |
|--------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| CMG Indications Exist           | The indication(s) must already be available in **CMG** and retrievable using the `cmgIndicationId`.                                               |
| CMG Offering ID                 | You must have the correct **CMG Deal ID** (`cmgOfferingId`) to associate the indication with.                                                     |
| cmgIndicationAction             | Required to define how the reflow should behave:<br>• **DUPLICATE** – Brio is empty but CMG marks it as duplicate<br>• **SUBMITTED** – CMG has the indication but Brio is missing it<br>• **MODIFIED** – CMG and Brio have mismatched details (e.g., quantity)<br>• **CANCELED** – Indication was canceled in CMG but is still active in Brio<br>• **DEDUPLICATE** – CMG marks that the data was a false duplicate |
| Swagger Access                  | Confirm access to [ReflowCmgIndicationsToBrio](https://cmgint.brio.fg.url.gs.com/swagger-ui/index.html#/indication-admin-facade/reflowCmgIndicationToBrio). |
| Sample Values                   | Refer to these for reference:<br>• **Offering ID**: `2b800000-f9d1-3242-e4ba-08dd5c2ad86d`<br>• **Indication ID**: `761a0000-678a-ba22-7be6-08dd5db9f5ad`<br>• **Example URL**: [Sample](https://gs.xc.uat.cmgecm.com/offerings/2b800000-f9d1-3242-e4ba-08dd5c2ad86d/order-book/indication-activity?cmgEntityKey=000000326&indicationId=761a0000-678a-ba22-7be6-08dd5db9f5ad) |
