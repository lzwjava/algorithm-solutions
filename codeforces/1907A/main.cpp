#include <algorithm>
#include <cassert>
#include <iostream>
#include <string>

using namespace std;

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    return 0;
}
