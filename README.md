speed

The error “no dynamic pipeline found” in GitLab usually occurs when using dynamic child pipelines, but the parent pipeline fails to generate the expected child pipeline configuration.

Since you’re running mutation testing, I assume your pipeline dynamically generates the test configurations. Here’s how you can debug this:

⸻

1. Check If the Parent Pipeline Generates the Child Pipeline

If you are using trigger or include for child pipelines, run:

cat .gitlab-ci.yml

Ensure the parent pipeline properly triggers a child pipeline. Your parent pipeline should have something like this:

generate-child-pipeline:
  stage: prepare
  script:
    - echo "Generating dynamic pipeline"
    - python generate_pipeline.py > generated-pipeline.yml
  artifacts:
    paths:
      - generated-pipeline.yml

trigger-child-pipeline:
  stage: test
  trigger:
    include: generated-pipeline.yml

Debug:
	•	Check whether generated-pipeline.yml is actually being created in the parent pipeline.
	•	Look at pipeline artifacts to see if generated-pipeline.yml exists.
	•	Use cat generated-pipeline.yml in the script to verify the content.

⸻

2. Verify the Generated YAML

If generated-pipeline.yml is empty or invalid, GitLab will fail to find a dynamic pipeline.
	•	Check the syntax with:

gitlab-ci-lint generated-pipeline.yml


	•	Manually inspect the generated file.

⸻

3. Ensure include is Used Properly

If using include, it must be correctly structured:

include:
  - local: generated-pipeline.yml

Ensure that:
	•	The path to generated-pipeline.yml is correct.
	•	The file is being created before being included.

⸻

4. Check GitLab Pipeline Logs

Check the logs of the job that generates the pipeline:

ls -la
cat generated-pipeline.yml

If generated-pipeline.yml does not exist or is empty, the pipeline cannot proceed.

⸻

5. Look for GitLab Runner Issues

If the issue persists:
	•	Restart the GitLab Runner.
	•	Ensure the correct runner is assigned to the job.

⸻

Next Steps
	1.	Confirm the pipeline generates generated-pipeline.yml.
	2.	Inspect the file’s contents.
	3.	Validate the syntax.
	4.	Ensure correct usage of trigger or include.
	5.	Check GitLab logs for any unexpected behavior.

Would you like to share your .gitlab-ci.yml file or any logs? I can help debug further.