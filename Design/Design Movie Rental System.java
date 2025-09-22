/*
  Problem Link: https://leetcode.com/problems/design-movie-rental-system/
*/


// Approach (TreeSet for ordered storage, HashMap for quick lookup)
// T.C. : 
// MovieRentingSystem() -> O(n log n) for n entries (insert each entry into TreeSets)
// search(movie)        -> O(5) i.e. constant time to return first 5 cheapest shops
// rent(shop, movie)    -> O(log m + log r) per operation
//                         (log m to remove from available TreeSet of that movie, log r to insert into rented TreeSet)
// drop(shop, movie)    -> O(log m + log r) per operation
//                         (log m to insert back to available TreeSet, log r to remove from rented TreeSet)
// report()             -> O(5) i.e. constant time to return top 5 rented movies

// S.C. : 
// O(n + r) total
// available          -> O(n) (sum of all available movies)
// movieToShopPrice   -> O(n) (sum of all shop-price mappings)
// rented             -> O(r) (r = total rented movies)
class Rented implements Comparable<Rented>{

    int shop, movie, price;
    public Rented(int shop, int movie, int price){
        this.shop = shop;
        this.movie = movie;
        this.price = price;
    }

    public int compareTo(Rented other){
        if(this.price != other.price){
            return this.price - other.price;
        }else if(this.shop != other.shop){
            return this.shop - other.shop;
        }
        return this.movie - other.movie;
    }
}

class PriceShop implements Comparable<PriceShop>{

    int shop, price;
    public PriceShop(int shop, int price){
        this.shop = shop;
        this.price = price;
    }

    public int compareTo(PriceShop other){

        if(this.price != other.price){
            return this.price - other.price;
        }
        return this.shop - other.shop;
    }
}

class MovieRentingSystem {
        Map<Integer, TreeSet<PriceShop>> unrentedMovieMap;
        Map<String, Integer> ShopMoviePriceMap;
        TreeSet<Rented> movieReport;

    public MovieRentingSystem(int n, int[][] entries) {
        unrentedMovieMap = new HashMap<>(); // Available for Rent in each shop
        movieReport = new TreeSet<>(); //set of already rented movies
        ShopMoviePriceMap = new HashMap<>(); // Get price for a movie in a shop 

        for(int [] entry : entries){
            PriceShop ps = new PriceShop(entry[0], entry[2]);
            unrentedMovieMap.computeIfAbsent(entry[1], k -> new TreeSet<>()).add(ps);
            String ShopMovieStr = entry[0]+"$"+entry[1];
            ShopMoviePriceMap.put(ShopMovieStr, entry[2]);
        }

    }
    
    public List<Integer> search(int movie) {
        List<Integer> shops = new ArrayList<>();
        Set<PriceShop> temp = unrentedMovieMap.get(movie);
        if(temp == null) return shops;
        int count = 0;
        for(PriceShop ps : temp){
           int shopId = ps.shop;
           int price = ps.price;
           shops.add(shopId);
           count++;
           if(count == 5) break;
        }

        return shops;

    }
    
    public void rent(int shop, int movie) {
        String str = shop+"$"+movie;
        int price = ShopMoviePriceMap.get(str);
        unrentedMovieMap.get(movie).remove(new PriceShop(shop, price));
        movieReport.add(new Rented(shop, movie, price));
    }
    
    public void drop(int shop, int movie) {
        String str = shop+"$"+movie;
        int price = ShopMoviePriceMap.get(str);
        unrentedMovieMap.get(movie).add(new PriceShop(shop, price));
        movieReport.remove(new Rented(shop, movie, price));
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> reportList = new ArrayList<>();
        if(movieReport.isEmpty())  return reportList;

       int count = 0;
       for(Rented r : movieReport){
            List<Integer> list = new ArrayList<>();
            int shop = r.shop;
            int movie = r.movie;
            list.add(shop);
            list.add(movie); 
            reportList.add(list); 
            count++;
            if(count == 5) break;    

        }

        return reportList;
    }   
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
