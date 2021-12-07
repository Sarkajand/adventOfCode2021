package main.day07;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 {

    private int[] input = new int[]{1101, 1, 29, 67, 1102, 0, 1, 65, 1008, 65, 35, 66, 1005, 66, 28, 1, 67, 65, 20, 4, 0, 1001, 65, 1, 65, 1106, 0, 8, 99, 35, 67, 101, 99, 105, 32, 110, 39, 101, 115, 116, 32, 112, 97, 115, 32, 117, 110, 101, 32, 105, 110, 116, 99, 111, 100, 101, 32, 112, 114, 111, 103, 114, 97, 109, 10, 867, 253, 111, 269, 117, 150, 421, 508, 1073, 136, 247, 10, 1427, 802, 2, 492, 1302, 228, 2, 48, 113, 0, 741, 34, 107, 559, 514, 283, 372, 78, 423, 405, 1303, 360, 281, 1850, 367, 892, 1021, 930, 318, 80, 709, 349, 32, 203, 94, 1359, 456, 783, 62, 34, 1487, 245, 294, 749, 250, 1441, 8, 1388, 604, 324, 483, 696, 119, 294, 1478, 529, 189, 454, 785, 703, 13, 1099, 790, 402, 251, 919, 116, 318, 201, 893, 571, 3, 45, 756, 41, 65, 92, 21, 1903, 219, 32, 191, 1037, 177, 480, 232, 389, 1342, 1178, 1320, 955, 1020, 655, 276, 203, 221, 316, 689, 621, 270, 911, 537, 230, 327, 662, 552, 410, 1608, 385, 7, 26, 227, 71, 1646, 257, 725, 531, 413, 8, 19, 1029, 182, 1518, 270, 124, 113, 569, 468, 126, 505, 376, 367, 113, 425, 4, 80, 1883, 433, 1167, 768, 231, 393, 528, 69, 422, 17, 350, 858, 1028, 659, 972, 108, 542, 602, 1577, 11, 1481, 127, 466, 415, 567, 1178, 38, 137, 777, 446, 965, 832, 1347, 642, 716, 176, 264, 487, 32, 425, 354, 104, 230, 756, 310, 711, 228, 580, 520, 677, 781, 45, 926, 1063, 126, 235, 262, 199, 330, 874, 1570, 221, 107, 803, 810, 1723, 266, 99, 940, 21, 38, 1680, 44, 32, 17, 907, 403, 413, 628, 968, 138, 12, 24, 483, 114, 658, 206, 24, 61, 561, 882, 532, 1280, 255, 805, 75, 237, 321, 310, 1022, 545, 1515, 609, 65, 791, 933, 233, 846, 506, 704, 628, 516, 868, 726, 134, 6, 243, 1048, 227, 259, 1599, 117, 114, 461, 365, 63, 1559, 62, 98, 884, 11, 426, 915, 192, 901, 4, 1481, 122, 424, 307, 250, 256, 693, 162, 1217, 834, 516, 644, 898, 396, 1073, 642, 480, 361, 1434, 607, 23, 818, 515, 6, 288, 443, 324, 4, 1559, 659, 409, 415, 82, 41, 1233, 657, 93, 1405, 17, 94, 18, 379, 32, 8, 419, 1511, 766, 234, 818, 916, 775, 4, 1009, 282, 372, 317, 371, 945, 1314, 261, 485, 529, 1076, 298, 223, 40, 434, 401, 117, 1030, 153, 2, 19, 27, 41, 544, 477, 1117, 588, 206, 155, 12, 1197, 1518, 305, 51, 921, 775, 296, 1187, 57, 517, 2, 36, 145, 92, 67, 68, 559, 771, 1, 69, 250, 612, 94, 1638, 1327, 501, 434, 114, 6, 1468, 429, 28, 1163, 207, 576, 50, 1759, 216, 9, 50, 432, 598, 664, 1087, 409, 828, 1115, 169, 120, 318, 21, 1245, 314, 338, 47, 469, 231, 236, 892, 671, 373, 991, 1136, 488, 341, 168, 143, 850, 1135, 42, 449, 666, 814, 16, 232, 505, 122, 1316, 803, 1093, 977, 79, 5, 936, 512, 217, 942, 1333, 13, 13, 1861, 2, 267, 74, 1096, 1058, 107, 461, 78, 418, 861, 547, 25, 1398, 255, 562, 344, 820, 1171, 1376, 494, 17, 116, 1333, 256, 20, 1425, 1668, 79, 604, 1614, 223, 45, 18, 917, 30, 965, 866, 1331, 91, 141, 1120, 829, 3, 0, 498, 57, 78, 1579, 467, 185, 1399, 683, 590, 11, 913, 33, 540, 536, 459, 367, 175, 176, 946, 130, 324, 634, 671, 554, 277, 570, 968, 409, 468, 419, 1249, 1039, 45, 238, 4, 808, 1022, 10, 151, 1158, 32, 38, 1054, 969, 90, 70, 1194, 1582, 512, 876, 289, 1042, 91, 1872, 305, 996, 349, 17, 517, 968, 1493, 637, 142, 141, 226, 590, 181, 811, 608, 4, 135, 97, 389, 385, 929, 1143, 1319, 684, 509, 437, 133, 843, 101, 118, 71, 120, 80, 25, 33, 259, 894, 1050, 1450, 583, 1665, 372, 128, 586, 282, 1147, 1160, 1643, 1488, 339, 445, 268, 1577, 101, 8, 308, 719, 210, 288, 332, 1034, 47, 1303, 31, 59, 16, 270, 104, 68, 1107, 736, 420, 108, 367, 461, 791, 279, 863, 645, 2, 999, 453, 682, 21, 764, 244, 435, 1238, 36, 1193, 37, 346, 35, 70, 114, 78, 67, 1245, 15, 1002, 83, 450, 353, 50, 396, 1068, 26, 21, 429, 551, 13, 498, 117, 731, 601, 23, 1218, 271, 26, 958, 852, 139, 331, 92, 560, 218, 1243, 410, 109, 296, 35, 588, 6, 645, 87, 64, 188, 497, 28, 693, 18, 88, 196, 62, 7, 33, 311, 1102, 187, 829, 664, 630, 331, 304, 1249, 21, 309, 1238, 64, 155, 38, 134, 291, 77, 90, 32, 765, 332, 87, 257, 755, 93, 181, 174, 118, 584, 98, 825, 292, 428, 187, 731, 813, 784, 1222, 117, 345, 1380, 31, 1447, 269, 672, 747, 1112, 147, 32, 690, 1258, 253, 763, 92, 1427, 503, 4, 40, 289, 41, 733, 240, 884, 201, 136, 594, 560, 3, 1083, 1282, 686, 918, 667, 1535, 702, 158, 65, 1055, 100, 481, 457, 1565, 1067, 641, 289, 18, 1537, 62, 545, 401, 1238, 528, 713, 1042, 430, 144, 390, 220, 953, 42, 817, 18, 26, 137, 1870, 999, 557, 234, 586, 1316, 87, 104, 369, 39, 215, 595, 922, 1194, 187, 1056, 382, 397, 387, 872, 191, 464, 1841, 883, 162, 119, 38, 916, 2, 676, 1524, 315, 1217, 63, 382, 328, 591, 372, 138, 883, 733, 910, 635, 1059, 87, 773, 630, 1179, 169, 947, 401, 20, 820, 119, 575, 1117, 48, 268, 45, 896, 772, 293, 217, 73, 732, 26, 528, 1121, 382, 813, 419, 424, 221, 107, 145, 264, 526, 589, 482, 51, 1399, 954, 292, 276, 248, 1276, 218, 1005, 296, 360, 60, 5, 499, 661, 192, 199, 250, 1001, 496, 281, 361, 664, 248, 1090, 86, 203, 241, 61, 329, 1551, 182, 790, 787, 408, 442, 603, 681, 522, 478, 1072, 527, 1094, 104, 1267, 418, 730, 217, 1198, 859};


    public void getPosition() {
//        input = new int[]{16, 1, 2, 0, 4, 2, 7, 1, 2, 14};

        int mean = Arrays.stream(input).sum() / input.length;

        int getToMeanMinus1 = countFuelForPosition(input, mean - 1);
        int getToMean = countFuelForPosition(input, mean);
        int getToMeanPlus1 = countFuelForPosition(input, mean + 1);

        if (getToMean < getToMeanMinus1 && getToMean < getToMeanPlus1) {
            System.out.println("position: " + mean);
            System.out.println("fuel: " + getToMean);
        } else {

            int lastFuel = Math.max(getToMeanPlus1, getToMeanMinus1);
            int fuel = getToMean;
            int position = mean;
            int direction = getToMeanMinus1 < getToMeanPlus1 ? -1 : 1;

            while (lastFuel > fuel) {
                lastFuel = countFuelForPosition(input, position);
                position = position + direction;
                fuel = countFuelForPosition(input, position);
            }

            System.out.println("position: " + (position - direction));
            System.out.println("fuel: " + lastFuel);
        }
    }

    public void optimizedFirstExample() {
//        input = new int[]{16, 1, 2, 0, 4, 2, 7, 1, 2, 14};

        List<Integer> inputList = Arrays.stream(input).boxed().collect(Collectors.toList());
        Collections.sort(inputList);
        int position = inputList.get(inputList.size()/2);

        System.out.println("position: " + position + ", fuel: " + countFuelForPosition(input, position));
    }

    private int countFuelForPosition(int[] input, int position) {
        return Arrays.stream(input).map(x -> Math.abs(x - position)).sum();
    }

    private int countFuelForPosition2(int[] input, int position) {
        return Arrays.stream(input).map(x -> {
            int sum = 0;
            for (int i = 1; i <= Math.abs(x - position); i++) {
                sum = sum + i;
            }
            return sum;
        }).sum();
    }
}
