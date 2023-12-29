// https://github.com/actium/codeforces/blob/master/1900/10/1915f.cpp
#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

using integer = unsigned long long;

template <typename T, typename U>
istream& operator>>(istream& input, pair<T, U>& v)
{
    return input >> v.first >> v.second;
}

template <typename T>
istream& operator>>(istream& input, vector<T>& v)
{
    for (T& a : v)
        input >> a;

    return input;
}

void answer(integer x)
{
    cout << x << '\n';
}

class BinaryIndexedTree {
public:
    explicit BinaryIndexedTree(size_t size)
        : data_(1 + size)
    {
    }

    void update(size_t index, int delta)
    {
        const size_t n = data_.size();
        for (size_t i = 1 + index; i < n; i += i & -i)
            data_[i] += delta;
    }

    unsigned prefix_sum(size_t index) const
    {
        unsigned s = 0;
        for (size_t i = 1 + index; i > 0; i &= i - 1)
            s += data_[i];

        return s;
    }

private:
    vector<unsigned> data_;

}; // class BinaryIndexedTree

struct Event {
    size_t who;
    int when;
    int what;
};

bool operator<(const Event& lhs, const Event& rhs)
{
    return lhs.when < rhs.when;
}

void solve(const vector<pair<int, int>>& s)
{
    const size_t n = s.size();

    vector<int> x(2 * n);
    for (size_t i = 0; i < n; ++i) {
        x[i * 2 + 0] = s[i].first;
        x[i * 2 + 1] = s[i].second;
    }

    sort(x.begin(), x.end());

    const auto index = [&](int p) {
        const auto it = lower_bound(x.begin(), x.end(), p);
        return it - x.begin();
    };

    vector<Event> es(2 * n);
    for (size_t i = 0; i < n; ++i) {
        es[2 * i + 0] = { i, s[i].first, 1 };
        es[2 * i + 1] = { i, s[i].second, -1 };
    }

    sort(es.begin(), es.end());

    integer k = 0;

    BinaryIndexedTree bit(2 * n);
    for (const Event& e : es) {
        if (e.what == 1) {
            bit.update(index(e.when), 1);
        } else {
            const size_t p = index(s[e.who].first);
            bit.update(p, -1);
            k += bit.prefix_sum(p);
        }
    }

    answer(k);
}

void test_case()
{
    size_t n;
    cin >> n;

    vector<pair<int, int>> s(n);
    cin >> s;

    solve(s);
}

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    cin.tie(nullptr)->sync_with_stdio(false);

    size_t t;
    cin >> t;

    while (t-- > 0)
        test_case();

    return 0;
}
