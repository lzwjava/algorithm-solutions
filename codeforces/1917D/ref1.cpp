#include <iostream>
#include <vector>

using namespace std;

void update(vector<int>& bitree, vector<int>& arr, int index, int value)
{
    int n = arr.size();
    arr[index] += value;
    while (index < n) {
        bitree[index] += value;
        index += (index & -index);
    }
}

int query(vector<int>& bitree, int index)
{
    int sum = 0;
    while (index > 0) {
        sum += bitree[index];
        index -= (index & -index);
    }
    return sum;
}

int main()
{
    vector<int> arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
    int n = arr.size();

    vector<int> bitree(n + 1, 0);

    int index_to_update = 2;
    int value_to_add = 5;
    update(bitree, arr, index_to_update, value_to_add);

    int index_to_query = 4;
    int prefix_sum = query(bitree, index_to_query);

    cout << "Updated array: ";
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;

    cout << "Prefix sum up to index " << index_to_query << ": " << prefix_sum << endl;

    return 0;
}
