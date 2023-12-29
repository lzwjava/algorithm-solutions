#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

struct Event {
    int time;
    int type;
};

bool compareEvents(const Event& a, const Event& b)
{
    return a.time < b.time || (a.time == b.time && a.type > b.type);
}

int countIntersections(vector<pair<int, int>>& segments)
{
    vector<Event> events;

    for (auto& segment : segments) {
        events.push_back({ segment.first, 1 });
        events.push_back({ segment.second, -1 });
    }

    sort(events.begin(), events.end(), compareEvents);

    int intersections = 0;
    int activeSegments = 0;

    for (const Event& event : events) {
        if (event.type == 1) {
            activeSegments++;
        } else {
            intersections += activeSegments - 1;
            activeSegments--;
        }
    }

    return intersections;
}

int main()
{
    freopen("in1.txt", "r", stdin);
    int n;
    cin >> n;

    vector<pair<int, int>> segments(n);

    for (int i = 0; i < n; i++) {
        cin >> segments[i].first >> segments[i].second;
    }

    int result = countIntersections(segments);

    cout << "Number of intersections: " << result << endl;

    return 0;
}
