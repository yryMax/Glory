package CodeForce.P670C;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Cinema_MySolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberScientists = scanner.nextInt();
        int[] scientistLanguage = new int[numberScientists];
        for (int i = 0; i < numberScientists; i++)
            scientistLanguage[i] = scanner.nextInt();
        int numberMovies = scanner.nextInt();
        Integer[][] movies = new Integer[numberMovies][3];
        for (int i = 0; i < numberMovies; i++)
            movies[i][0] = scanner.nextInt();
        for (int i = 0; i < numberMovies; i++)
            movies[i][1] = scanner.nextInt();
        for (int i = 0; i < numberMovies; i++)
            movies[i][2] = i;

        System.out.println(findTheBestMovie(scientistLanguage, movies) + 1);
    }

    private static int findTheBestMovie(int[] scientistLanguage, Integer[][] movies) {
        Map<Integer, Integer> languagesStatistics = new HashMap<>();
        for (int language : scientistLanguage)
            languagesStatistics.put(language, languagesStatistics.getOrDefault(language, 0) + 1);

        Arrays.sort(movies, (movie1, movie2) -> (languagesStatistics.getOrDefault(movie1[0], 0).intValue() != languagesStatistics.getOrDefault(movie2[0], 0).intValue())
                ? - languagesStatistics.getOrDefault(movie1[0], 0) + languagesStatistics.getOrDefault(movie2[0], 0)
                : - languagesStatistics.getOrDefault(movie1[1], 0) + languagesStatistics.getOrDefault(movie2[1], 0));
        return movies[0][2];
    }
}
