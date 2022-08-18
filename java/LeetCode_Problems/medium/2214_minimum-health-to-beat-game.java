ass Solution {
	    public long minimumHealth(int[] damage, int armor) {
		            
		            long health = 1; 
			            int max = 0; 
				            
				            for (int i = 0; i < damage.length; i++) {
						                health += damage[i];
								            max = Math.max(max, damage[i]);
									                
									                
									            }
					            
					            health -= Math.min(max, armor);
						            return health; 
							        }
}

