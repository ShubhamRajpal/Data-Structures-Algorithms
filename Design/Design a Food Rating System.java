/*
  Problem Link: https://leetcode.com/problems/design-a-food-rating-system/
*/

// Approach-1(using PQ for storing pairs {food, rating} )
// T.C: O(nlogn + mlog(n+m))
// FoodRatings - O(nlogn) ,  logn = for inserting pairs into PQ
// changeRating - O(m*log(m+n)) because in worst case, the priority queue can contain (n+m) elements (where m = No.of calls made to changeRating and highestRated)
// highestRated - O(m*log(m+n)) because we might need to remove invalid pairs from PQ
// S.C: O(n+m)

class Pair implements Comparable<Pair>{
    String foodItem;
    int rating;

    public Pair(String fi, int r) {
        foodItem = fi;
        rating = r;
    }

    public int compareTo(Pair p) {
        if (rating != p.rating) {
            return p.rating - rating;
        }

        return foodItem.compareTo(p.foodItem);
    }
}

class FoodRatings {
    Map<String, String> foodToCuisineTypeMp;
    Map<String, Integer> foodToRatingMp;
    Map<String, PriorityQueue<Pair>> cuisineTypeToFoodMp;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisineTypeMp = new HashMap<>();
        foodToRatingMp = new HashMap<>();
        cuisineTypeToFoodMp = new HashMap<>();
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            foodToCuisineTypeMp.put(foods[i], cuisines[i]);
            foodToRatingMp.put(foods[i], ratings[i]);
            cuisineTypeToFoodMp.computeIfAbsent(cuisines[i], k -> new PriorityQueue<>())
                    .add(new Pair(foods[i], ratings[i]));
        }

    }

    public void changeRating(String food, int newRating) {
        foodToRatingMp.put(food, newRating);
        String foodCuisine = foodToCuisineTypeMp.get(food);
        cuisineTypeToFoodMp.get(foodCuisine).add(new Pair(food, newRating));

    }

    public String highestRated(String cuisine) {
        Pair highestRated = cuisineTypeToFoodMp.get(cuisine).peek();
        int curHighestRating = foodToRatingMp.get(highestRated.foodItem);
        while(curHighestRating != highestRated.rating){
            cuisineTypeToFoodMp.get(cuisine).poll();
            highestRated = cuisineTypeToFoodMp.get(cuisine).peek();
            curHighestRating = foodToRatingMp.get(highestRated.foodItem);
        }

        return highestRated.foodItem;
    }
}



// Approach-2(using TreeSet for storing pairs {food, rating} )
// T.C: O((n+m) * logn)
// FoodRatings - O(nlogn) ,  logn = for inserting pairs into PQ
// changeRating - O(mlogn) deletion and insertion in sortedSet takes log n time , where m = No.of calls made to changeRating and highestRated
// highestRated - O(mlogn) 
// S.C: O(n)

class Pair implements Comparable<Pair> {
    String foodItem;
    int rating;

    public Pair(String fi, int r) {
        foodItem = fi;
        rating = r;
    }

    public int compareTo(Pair p) {
        int compareByRating = Integer.compare(p.rating, rating);
        if (compareByRating == 0) {
            return p.foodItem.compareTo(foodItem);
        }

        return compareByRating;
    }
}

class FoodRatings {
    Map<String, String> foodToCuisineTypeMp;
    Map<String, Integer> foodToRatingMp;
    Map<String, TreeSet<Pair>> cuisineTypeToFoodMp;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {

        foodToCuisineTypeMp = new HashMap<>();
        foodToRatingMp = new HashMap<>();
        cuisineTypeToFoodMp = new HashMap<>();

        int n = foods.length;
        for (int i = 0; i < n; i++) {
            foodToCuisineTypeMp.put(foods[i], cuisines[i]);
            foodToRatingMp.put(foods[i], ratings[i]);
            cuisineTypeToFoodMp.computeIfAbsent(cuisines[i], k -> new TreeSet<Pair>())
                    .add(new Pair(foods[i], -ratings[i]));
        }

    }

    public void changeRating(String food, int newRating) {

        String cuisineName = foodToCuisineTypeMp.get(food);
        TreeSet<Pair> cuisineSet = cuisineTypeToFoodMp.get(cuisineName);
        int oldRating = foodToRatingMp.get(food);
        Pair oldValue = new Pair(food, -oldRating);
        cuisineSet.remove(oldValue);
        foodToRatingMp.put(food, newRating);
        cuisineSet.add(new Pair(food, -newRating));

    }

    public String highestRated(String cuisine) {

         //first item will have highest absolute rating "we put all ratings in negative" (TreeSet)
        //Why did we not put them in ascending order and just return the last element of TreeSet ?
        //Ans : Because We can have a case like below
        //{(15, abc), (16, ramen), (16, sushi)} -> Order in TreeSet. last element is not lexicographically smaller
        //{(-16, ramen), (-16, sushi), (-15, abc)} -> First element is now lexicographically smaller

        Pair highestRated = cuisineTypeToFoodMp.get(cuisine).last();
        return highestRated.foodItem;
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
