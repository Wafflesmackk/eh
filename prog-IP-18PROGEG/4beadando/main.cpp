#include <iostream>


using namespace std;



struct coor
{
    int x, y;
};

struct Cell
{
    coor pos;
    int height;
    bool csu; // csucs-e
    int neighbours;
};






int main()
{
    coor Map;
    int K;
    cin >> Map.y >> Map.x >> K;
    while (Map.x < 1 || Map.y < 1 || K < 1 || Map.x > 100 || Map.y > 100 || K > min(Map.x, Map.y) )
    {
        cout << "Hibas adat" << endl;
        cin.clear();
        cin.ignore(32676, '\n');
        cin >> Map.y >> Map.x >> K;
    }

    Cell cells[Map.y+1][Map.x+1];

   for (int i = 1; i <= Map.y; i++)
    {
        for (int j = 1; j <= Map.x; j++)
        {
            cin >> cells[i][j].height;
            while (cells[i][j].height < 0 && cells[i][j].height > 1000)
            {
                cout << "Hibas adat" << endl;
                cin.clear();
                cin.ignore(32676, '\n');
                cin >> cells[i][j].height;
            }
            cells[i][j].pos.x = j;
            cells[i][j].pos.y = i;
            cells[i][j].neighbours = 0;
        }
    }

    for (int i = 1; i <= Map.y; i++)
    {
        for (int j = 1; j <= Map.x; j++)
        {
            if (i - 1 < 1 || i + 1 > Map.y || j + 1 > Map.x || j - 1 < 1)
            {
                cells[i][j].csu = { false };
            }
            else if (cells[i][j].height > cells[i][j - 1].height && cells[i][j].height > cells[i][j + 1].height && cells[i][j].height > cells[i + 1][j].height && cells[i][j].height > cells[i - 1][j].height)
            {
                cells[i][j].csu = { true };
            }
            else
            {
                cells[i][j].csu = { false };
            }
        }
    }


    for (int i = 1; i <= Map.y; i++)
    {
        for (int j = 1; j <= Map.x; j++)
        {
            for (int k = 0; k < K; k++)
            {
                for (int l = 0; l < K; l++)
                {
                    if ( i + k > Map.y || j + l > Map.x )
                    {
                        continue;
                    }
                    else if (cells[i + k][j + l].csu == 1)
                    {
                        cells[i][j].neighbours++;
                    }
                }
            }
        }
    }

    Cell max;
    max.pos.x = 101;
    max.pos.y = 101;
    max.neighbours = 0;
    for (int i = 1; i <= Map.y; i++)
    {
        for (int j = 1; j <= Map.x; j++)
        {
            if (cells[i][j].neighbours > max.neighbours )
            {
                    max.pos.x = j;
                    max.pos.y = i;
                    max.neighbours = cells[i][j].neighbours;
            }
            else if (cells[i][j].neighbours == max.neighbours && cells[i][j].pos.y < max.pos.y)
            {
                max.pos.x = j;
                max.pos.y = i;
            }
            else if (cells[i][j].neighbours == max.neighbours && cells[i][j].pos.y == max.pos.y && cells[i][j].pos.x < max.pos.x)
            {
                max.pos.x = j;
            }
            else
            {
                continue;
            }

        }
    }

    cout << max.pos.y <<" "<< max.pos.x;
    return 0;
}

