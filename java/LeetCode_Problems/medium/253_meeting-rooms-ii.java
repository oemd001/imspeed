ass Solution {
	    public int minMeetingRooms(int[][] intervals) {
		            /*
			     *         If we see the keyword "minheap", we should think priorty queue
			     *                 
			     *                         1. (easy parts) -> sort the array based on the starting values
			     *                                 2. (easy part) -> Utilize the priority queue to keep track of the ending times
			     *                                         3. If we discover that the most recent element on the priority queue is less than or 
			     *                                                     equal to the start time of the next meeting, we want to remove that meeting on the priority queue
			     *                                                                 (pq.poll())
			     *                                                                                 The min heap in this case will basically do the following: get the minimum value from the very top. 
			     *                                                                                                 Once we get the value from the very top, we compare it to the next interval value
			     *                                                                                                                 After we do that, if we discover the first index of intervals to be greater than or equal to the top most element
			     *                                                                                                                                     in the priority queue, we can be confident to say that the room can be opened. 
			     *                                                                                                                                                     Otherwise, we just allocate another room. 
			     *                                                                                                                                                             4. After that, continue to add more int[] to priority queue. 
			     *                                                                                                                                                                     */
		            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
			            
			            PriorityQueue<int[]> pq =new PriorityQueue<>((a,b)->(a[1] - b[1]));
				            
				            for (int[] interval : intervals) {
						                if (!pq.isEmpty() && pq.peek()[1] <= interval[0]) {
									                pq.poll(); 
											            }
								            pq.add(interval);
									            }
					            
					            return pq.size(); 
						            
						            
						            
						        }
	        public int first_attempt(int[][] intervals) {
			        /*
				 *         This problem is similar to the merge intervals. Should probably refresh on that first
				 *                 
				 *                         We are trying to find the minimum number of conference rooms needed
				 *                                     What that means is this:
				 *                                                 [7,10] and [2,4] will require 1 conference room
				 *                                                             (There is no meeting that is occuring concurrently)
				 *                                                                     Simple way:
				 *                                                                                 We can technically do this:
				 *                                                                                             [[0,30],[5,10],[15,20]]
				 *                                                                                                         0,30 -> needs its own conference room
				 *                                                                                                                     5,10 and 15,20 can share
				 *                                                                                                                                 (so, 2)
				 *                                                                                                                                         
				 *                                                                                                                                                 Initial thought process
				 *                                                                                                                                                             1. If there are more than 2 of the same start times, conf_count++; 
				 *                                                                                                                                                                         2. Handling overlap times (and close to overlap times)
				 *                                                                                                                                                                                     first1 -> 0
				 *                                                                                                                                                                                                 first2 -> 30
				 *                                                                                                                                                                                                             second1 -> 5
				 *                                                                                                                                                                                                                         second2 -> 10
				 *                                                                                                                                                                                                                                     
				 *                                                                                                                                                                                                                                                 if (first2 > second1) conf_count++ --> we know that first1 will require a conference room, so continue
				 *                                                                                                                                                                                                                                                             (else) if (first2 < second1), we can continue because we know that they can rotate with one conf room
				 *                                                                                                                                                                                                                                                                         first1 -> 5
				 *                                                                                                                                                                                                                                                                                     first2 -> 10
				 *                                                                                                                                                                                                                                                                                                 second1 -> 15
				 *                                                                                                                                                                                                                                                                                                             second2 -> 20
				 *                                                                                                                                                                                                                                                                                                                         (at the end, iterate conf_room by one)
				 *                                                                                                                                                                                                                                                                                                                                     
				 *                                                                                                                                                                                                                                                                                                                                                 Edge case:
				 *                                                                                                                                                                                                                                                                                                                                                             What if the first element is [0, 14]
				 *                                                                                                                                                                                                                                                                                                                                                                         Maybe keeping a "max" value would work so when we run the condition (first2 < second1), we should check
				 *                                                                                                                                                                                                                                                                                                                                                                                     if the current first2 is greater/equal to max (longest meeting) and after that, no new conf_room needs to be
				 *                                                                                                                                                                                                                                                                                                                                                                                                 added
				 *                                                                                                                                                                                                                                                                                                                                                                                                         */
			        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); 
				        if (intervals.length == 1) return 1; 
					        
					        int[] current_interval = intervals[0];
						        List<int[]> list = new ArrayList<>(); 
							        list.add(current_interval);
								        
								        int conf_room = 0; 
									        int max_hog = -1; 
										        
										        
										        for (int[] temp : intervals) {
												            int first1 = current_interval[0];
													                int first2 = current_interval[1];
															            int second1 = temp[0];
																                int second2 = temp[1];
																		            
																		            if (first2 < second1) {
																				                    current_interval[1] = Math.max(first2, second2);
																						                }
																			                else {
																						                current_interval = temp; 
																								                list.add(current_interval);
																										            }
																					        }
											        
											        return list.size(); 
												    }
}

