speed


⸻

Table 1: Reflow Indications from Brio to CMG (Short)

#	Scenario	Pre-Requisites	Testing Location	Procedure	Expected Outcome	Actions / Follow Ups
1	Reflow Indications from Brio to CMG(CMG Indications Missing)	- CMG Indications are not found or out-of-sync- Brio has valid indications to be reflowed	Swagger EndpointReflowBrioIndicationsToCmg	1. Execute Reflow with BrioId tied to a valid dealId.2. Observe the response code (e.g., 200).	Brio indications are successfully reflowed to CMG.	1. Confirm the reflowed indications appear in CMG.2. Check logs for any errors or warnings.3. Ensure no discrepancies remain.



⸻

Table 2: Reflow Indications from CMG to Brio (Detailed, Step-by-Step)

#	Scenario	Pre-Requisites	Testing Location	Procedure	Expected Outcome	Actions / Follow Ups
1	Reflow Indications from CMG to Brio(Brio Indications Missing/Mismatched)	- CMG Indications exist in the system.- Brio Indications need updating or are mismatched.	Swagger EndpointReflowCmgIndicationsToBrio	Step 1: Open the CMG Indication Admin Facade  • Validate the existing CMG Indication ID.  • Confirm the correct CMG Offering ID is provided.	- The Admin Facade is accessed successfully.- Correct Indication/Offering IDs are confirmed.	- If required IDs are missing or incorrect, correct them before proceeding.- Document any issues in the system logs.
2	Reflow Indications from CMG to Brio(Brio Indications Missing/Mismatched)	- CMG Indications exist in the system.- Brio Indications need updating or are mismatched.	Swagger EndpointReflowCmgIndicationsToBrio	Step 2: Initiate the Reflow  • Select cmgIndicationAction (SUBMITTED, MODIFIED, CANCELED, DUPLICATE, DEDUPLICATE).  • Provide the correct cmgOfferingId.  • Provide the correct cmgIndicationId.	- The reflow request initiates successfully.- A 200 response code indicates success.	- Check logs for any errors or warnings.- Ensure the action and ID parameters match the scenario.- Retry if the response code indicates failure.
3	Reflow Indications from CMG to Brio(Brio Indications Missing/Mismatched)	- CMG Indications exist in the system.- Brio Indications need updating or are mismatched.	Swagger EndpointReflowCmgIndicationsToBrio	Step 3: Verify the Reflow  • Check the response code again (e.g., 200).  • Confirm the reflowed data in Brio matches the CMG record.	- Brio Indications are updated/created accurately to match CMG.	- Confirm the newly updated or created indications appear in Brio.- If discrepancies remain, notify the Brio Admin team.- Document any unresolved issues for follow-up.



⸻
