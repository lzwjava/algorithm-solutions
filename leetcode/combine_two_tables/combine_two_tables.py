import pandas as pd


def combine_two_tables(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    print(person)
    print(address)
    return None


person = pd.read_csv('person.csv')
address = pd.read_csv('address.csv')
print(combine_two_tables(person, address))
