#include <iostream>
#include <vector>

class BinaryIndexedTree {
public:
    BinaryIndexedTree(int size)
        : data_(size + 1, 0)
    {
    }

    void update(int idx, int delta)
    {
        idx++;
        while (idx < data_.size()) {
            data_[idx] += delta;
            idx += idx & -idx;
        }
    }

    int prefixSum(int idx)
    {
        idx++;
        int sum = 0;
        while (idx > 0) {
            sum += data_[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

private:
    std::vector<int> data_;
};

int main()
{
    std::vector<int> arr = { 1, 2, 3, 4, 5 };
    int n = arr.size();

    BinaryIndexedTree bit(n);

    for (int i = 0; i < n; i++) {
        bit.update(i, arr[i]);
    }

    for (int i = 0; i < n; i++) {
        int ps = bit.prefixSum(i);
        std::cout << ps << std::endl;
    }

    return 0;
}
