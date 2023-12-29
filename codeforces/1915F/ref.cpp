// https://github.com/actium/codeforces/blob/master/1900/10/1915f.cpp
#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>
#include <vector>

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
    for (T& item : v)
        input >> item;

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
        unsigned sum = 0;
        for (size_t i = 1 + index; i > 0; i &= i - 1)
            sum += data_[i];

        return sum;
    }

private:
    vector<unsigned> data_;
};

struct Event {
    size_t id;
    int time;
    int type;
};

bool operator<(const Event& lhs, const Event& rhs)
{
    return lhs.time < rhs.time;
}

void solve(const vector<pair<int, int>>& segments)
{
    const size_t n = segments.size();

    vector<int> endpoints(2 * n);
    for (size_t i = 0; i < n; ++i) {
        endpoints[i * 2 + 0] = segments[i].first;
        endpoints[i * 2 + 1] = segments[i].second;
    }

    sort(endpoints.begin(), endpoints.end());

    const auto index = [&](int point) {
        return lower_bound(endpoints.begin(), endpoints.end(), point) - endpoints.begin();
    };

    vector<Event> events(2 * n);
    for (size_t i = 0; i < n; ++i) {
        events[2 * i + 0] = { i, segments[i].first, 1 };
        events[2 * i + 1] = { i, segments[i].second, -1 };
    }

    sort(events.begin(), events.end());

    integer count = 0;
    BinaryIndexedTree bit(2 * n);
    for (const Event& e : events) {
        if (e.type == 1) {
            bit.update(index(e.time), 1);
        } else {
            const size_t p = index(segments[e.id].first);
            bit.update(p, -1);
            count += bit.prefix_sum(p);
        }
    }

    answer(count);
}

void test_case()
{
    size_t n;
    cin >> n;

    vector<pair<int, int>> segments(n);
    cin >> segments;

    solve(segments);
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
