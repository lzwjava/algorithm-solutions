#include <ctime>
#include <iostream>
#include <random>
#include <vector>

// Function to generate random test data for one test case
void generateRandomTestCase(int n)
{
    std::cout << n << std::endl;

    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<int> dist(-1e9, 1e9);

    for (int i = 0; i < n; ++i) {
        int ai = dist(gen);
        int bi;
        do {
            bi = dist(gen);
        } while (bi <= ai);

        std::cout << ai << " " << bi << std::endl;
    }
}

int main()
{
    std::srand(static_cast<unsigned int>(std::time(nullptr)));
    int t = 5; // Number of test cases

    for (int i = 0; i < t; ++i) {
        int n = std::rand() % 10 + 1; // Random number of people (1 to 10)
        generateRandomTestCase(n);
    }

    return 0;
}
