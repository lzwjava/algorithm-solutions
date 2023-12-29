#include <algorithm>
#include <ctime>
#include <fstream>
#include <iostream>
#include <random>
#include <set>
#include <vector>

using namespace std; // Use this directive to avoid

// Function to generate random test data for one test case
void generateRandomTestCa;

    uniform_int_distribution<int> dist(-max, maxse(int n)
{
    cout << n << endl;

    random_device rd;
    mt19937 gen(rd());
    // int max = 100000000;
    int max = 1000000;

    uniform_int_distribution<int> dist(-max, max);

    set<int> distinctNumbers;
    while (distinctNumbers.size() < 2 * n) {
        int randomNumber = dist(gen);
        distinctNumbers.insert(randomNumber);
    }

    vector<int> nums(distinctNumbers.begin(), distinctNumbers.end());
    random_shuffle(nums.begin(), nums.end());

    int count = 0;
    for (int number : nums) {
        cout << number << " ";
        count++;
        if (count % 2 == 0) {
            cout << endl;
        }
    }

    if (count % 2 != 0) {
        cout << endl;
    }
}

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "w", stdout);
    }
    srand(static_cast<unsigned int>(time(nullptr)));
    int t = 1; // Number of test cases

    cout << t << endl;

    for (int i = 0; i < t; ++i) {
        int pm = 20000;
        int n = rand() % pm + 1;
        generateRandomTestCase(n);
    }

    return 0;
}
