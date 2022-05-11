package AcWing.P271;

import java.util.*;

public class PicturePermutation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int numRow = Integer.parseInt(in.nextLine());
            if (numRow == 0) break;
            int[] nums = new int[5];
            for (int i = 0; i < numRow; i++)
                nums[i] = in.nextInt();
            long[][][][][] dp = new long[nums[0] + 1][nums[1] + 1][nums[2] + 1][nums[3] + 1][nums[4] + 1];
            dp[0][0][0][0][0] = 1;
            for (int dim1 = 0; dim1 <= nums[0]; dim1++) {
                for (int dim2 = 0; dim2 <= Math.min(dim1, nums[1]); dim2++) {
                    for (int dim3 = 0; dim3 <= Math.min(dim2, nums[2]); dim3++) {
                        for (int dim4 = 0; dim4 <= Math.min(dim3, nums[3]); dim4++) {
                            for (int dim5 = 0; dim5 <= Math.min(dim4, nums[4]); dim5++) {
                                if (numRow < 2 && dim2 >= 1) continue;
                                if (numRow < 3 && dim3 >= 1) continue;
                                if (numRow < 4 && dim4 >= 1) continue;
                                if (numRow < 5 && dim5 >= 1) continue;
                                if (dim1 != nums[0]) dp[dim1 + 1][dim2][dim3][dim4][dim5] += dp[dim1][dim2][dim3][dim4][dim5];
                                if (numRow > 1 && dim2 < dim1 && dim2 != nums[1]) dp[dim1][dim2 + 1][dim3][dim4][dim5] += dp[dim1][dim2][dim3][dim4][dim5];
                                if (numRow > 2 && dim3 < dim2 && dim3 != nums[2]) dp[dim1][dim2][dim3 + 1][dim4][dim5] += dp[dim1][dim2][dim3][dim4][dim5];
                                if (numRow > 3 && dim4 < dim3 && dim4 != nums[3]) dp[dim1][dim2][dim3][dim4 + 1][dim5] += dp[dim1][dim2][dim3][dim4][dim5];
                                if (numRow > 4 && dim5 < dim4 && dim5 != nums[4]) dp[dim1][dim2][dim3][dim4][dim5 + 1] += dp[dim1][dim2][dim3][dim4][dim5];
                            }
                        }
                    }
                }
            }
            System.out.println(dp[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]]);
            in.nextLine();

        }
    }
}
