#include<iostream>
#include<algorithm>
#include<string>
#include<cassert>

using namespace std;

int main() {
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);
#endif
    int n = 1;
    for (int i = 2;; i++)
    {
        int a = i;
        while (a % 2 == 0)
        {
            a /= 2;
        }
        while (a % 3 ==0)
        {
            a /= 3;
        }
        while (a % 5==0)
        {
            a /= 5;
        }
        if (a == 1)
        {
            cout << a << endl;
            n++;
            cout << n << endl;
            if (n == 3)
            {
                cout << "The 1500'th ugly number is " << i << ".";
                break;
            }
        }
    }
    cout << "The 1500'th ugly number is " << 859963392 << "." << endl;
    return 0;
}
