Stuff that I did @ Tesla: 
(not cracked, just basic stuff)
1. Created an UI that allowed our data uploaders to visualize what drive they're uploading.
2. Optimized data upload stations that utilized direct NVMe to PCIe connections because our existing USB data transfer speeds wasn't fast enough
3. Developed Tesla's CI platform for automatic deployment (when there is a patch to be sent) via Github Actions and Ansible
(A bit crazier)
1. Hot swapping NVMe drives (unsafe/safe releases): developed a linux driver that allowed for "on-the-fly" drive ejections from the PCIe so data uploaders can swap out drives on demand
2. Re-directed data modeling and upload traffic from AWS to TCS (Tesla Cloud Storage, the on-prem version of AWS S3) in 3 hours
3. Discovered hardware bug on our supplier NVMe enclosures (RealTek RTL9210B-CG) where the chip isn't able to handle data read/write speeds of 2,000 MB/s for over 30 seconds - contributed to a MR on the linux kernel
(???)
1. Current bottleneck at Tesla: compression speeds. Developed a MVP (Minimum viable product) for GPU-based compression. (Used nVcomp and other CUDA based parallel compression techniques)
2. Dojo: using my specifiations, built out parts of the DOJO supercomputer (see, Tesla DOJO) to handle data modeling and uploading traffic
3. Cost savings on data upload: discovered that it was cheaper to overnight ship (via FedEx/UPS) training data than it was to use the on-site mobile broadband. Created automatic shipping tracking/drive management system ship drives from operators from the United States to Tesla hubs (Albany, NY; Austin, TX; Palo Alto, CA; Seattle, WA)
4. Data upload metrics/infrastructure robustness: my system (still) transfers 15 PetaBytes of training data to Tesla Dojo/TCS daily.
5. Cost savings on moving off of AWS: $15 Million/month
6. Slim Jims eaten: 64, Monitors used (at once): 3, GPUs destroyed: (2), High fives to Elon: (1), Average hours worked per week: ~100

Side Quests @ Tesla
(AI Stuff)
1. Developed an in-house computer use AI agent that takes in streaming data to perform relevant actions
2. Created a data pipeline for training data to flow to DOJO Supercomputer (Tesla DOJO)
3. Voice Cloned Elon (short lived initative, got in trouble for that)


Stuff that I did @ GS so far: 
Reconciliaiton (I technically own this): 
1. On deals, performed indication, allocation and acknowledgement checks to ensure that the orderbook on GS services and on external vendor book services were matching
2. Developed a mechanism that enabled the automatic resolution/remediation between mismatches - minimizing delay between solutions
BTS/Roadshow:
1. Post-Deal Execution & Analytics required live data - developed a load balancing mechanism to handle requests (they sent 6,000 requests over )
2. UI: added roadshow data on to Intelligent Execution
E2E testing
1. Uplifted existing E2E workflows to reflect newer standards
