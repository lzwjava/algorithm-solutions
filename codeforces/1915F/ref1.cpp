#include <iostream>
#include <vector>

class BinaryIndexedTree {
public:
    BinaryIndexedTree(int size)
        : data_(size + 1, 0)
    {
    }

    void update(int index, int delta)
    {
        index++;
        while (index < data_.size()) {
            data_[index] += delta;
            index += index & -index;
        }
    }

    int prefixSum(int index)
    {
        index++;
        int sum = 0;
        while (index > 0) {
            sum += data_[index];
            index -= index & -index;
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
        int prefix_sum = bit.prefixSum(i);
        std::cout << prefix_sum << std::endl;
    }

    return 0;
}
