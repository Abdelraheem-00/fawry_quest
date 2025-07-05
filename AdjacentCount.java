public class AdjacentCount {
    public static boolean hasAdjacent(int[] arr, int value) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == value && arr[i + 1] == value) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 3, 10, 10, 2, 4, 1};
        int[] arr2 = {5, 3, 3, 5, 2, 4, 1};
        int target = 10;

        System.out.println("Has adjacent 10s (arr1)? " + hasAdjacent(arr1, target));
        System.out.println("Has adjacent 10s (arr2)? " + hasAdjacent(arr2, target));
    }
}
