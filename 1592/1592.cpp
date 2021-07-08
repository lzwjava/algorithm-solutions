#include<iostream>
#include<algorithm>
#include<string>
#include<cassert>
#include<vector>

using namespace std;

vector<string> split(string s, string delimiter) {
    size_t pos_start = 0, pos_end, delim_len = delimiter.length();
    string token;
    vector<string> res;
    while((pos_end = s.find(delimiter, pos_start)) != string::npos) {
        token = s.substr(pos_start, pos_end - pos_start);
        pos_start = pos_end + delim_len;
        res.push_back(token);
    }
    res.push_back(s.substr(pos_start));
    return res;
}

int main() {
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);
#endif
    int n, m;
    while(cin >> n) {
        cin >> m;
        cin.ignore();
        for (int i = 0; i < n; i++)
        {
            string line;
            getline(cin, line);
            // cout << line << endl;
            split(line, ",");
        }
    }
    return 0;
}
